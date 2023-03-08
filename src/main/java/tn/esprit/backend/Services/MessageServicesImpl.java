package tn.esprit.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.backend.Repository.*;
import tn.esprit.backend.model.*;
import tn.esprit.backend.utils.FileUpload;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class MessageServicesImpl implements MessageServices {
    @Autowired
    ReactRepository reactRepository;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ParticipantServices participantServices;

//    @Override
//    public List<String> fetchBadWords() {
//        List<String> badWords = new ArrayList<>();
//        try {
//            URL url = new URL("https://docs.google.com/spreadsheets/d/1hIEi2YG3ydav1E06Bzf2mQbGZ12kh2fe4ISgLg_UBuM/export?format=csv");
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            InputStream inputStream = connection.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] values = line.split(",");
//                if (values.length > 0) {
//                    badWords.add(values[0]);
//                }
//            }
//            bufferedReader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return badWords;
//    }
//
//}
    public List<String> fetchBadWords() {
        List<String> badWords = new ArrayList<>();
        try {
            URL url = new URL("https://docs.google.com/spreadsheets/d/1hIEi2YG3ydav1E06Bzf2mQbGZ12kh2fe4ISgLg_UBuM/export?format=csv");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 0) {
                    String badWord = values[0];
                    badWords.add(badWord.replaceAll("f", "*"));
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badWords;
    }

    @Override
    public ResponseEntity<Message> addTagToMember(Long roomId,String message) {

            int atIndex = message.indexOf('@');
            if (atIndex == -1) {
                return ResponseEntity.badRequest().build();// not founde
            }

            String domain = message.substring(atIndex + 1);
            String[] parts = domain.split("\\.");
          //  String usernameToCall = parts.length > 0 ? parts[parts.length - 1] : "";
            String usernameToCall = message.substring(atIndex + 1).split("\\s+")[0];

           List<User> members =participantServices.GetListOfMemvers(roomId);
           System.out.println(members);
        if (members.contains(usernameToCall)) {// when user don't exist
            System.out.println("no user with this name");
            System.out.println(usernameToCall);
            return ResponseEntity.badRequest().build();
            }else {

            Message msg = new Message();
            msg.setMessage(message);
            messageRepository.save(msg);
            System.out.println("userExist");

            System.out.println(message);
        }
        return ResponseEntity.ok().build();
        }




    @Override
  public Message createMessage(Message message, Long roomId, String sender) {
        Room room = roomRepository.findById(roomId).orElse(null);
       if (room != null) {
           message.setDateMessage(LocalDateTime.now());
           message.setRoom(room);
           message.setMsgType(MsgType.Text);

           User user =userRepository.findById(sender).orElse(null);
           message.setSender(user);
           String MessageWithEmoji = GetEmoji(message.getMessage());
           message.setMessage(MessageWithEmoji);
           return messageRepository.save(message);
       }
    return message;
   }
    @Override
    public String textMsg(Long roomId, String sender, Message msg) {
        System.out.println("refdisc : "+roomId+" sender : "+sender+" message : "+msg.getMessage());

        Room room = roomRepository.findById(roomId).orElse(null);

        room.setWasRead(Boolean.FALSE);
        String msgC = msg.getMessage();
//        try {
//            msgC = tn.esprit.backend.utils.MessagerieUtils.filterbadwords(msgC);
//            System.out.println(msgC);
//        } catch (FileNotFoundException e) {
//            System.out.println("fichier peut pas le lire");
//            e.printStackTrace();
//
//        }
        List<String> badWords = fetchBadWords();

        boolean containsBadWord = false;
        for (String badWord : badWords) {
            if (msg.getMessage().toLowerCase().contains(badWord.toLowerCase())) {
                containsBadWord = true;
                break;
            }
        }

        if (containsBadWord) {
            String er="***" ;
            return er;

        }
        msg.setMessage(msgC);
        User Sender =userRepository.findById(sender).orElse(null);
        msg.setSender(Sender);
        msg.setMsgType(MsgType.Text);
        msg.setDateMessage(LocalDateTime.now());
        String MessageWithEmoji = GetEmoji(msg.getMessage());
        msg.setMessage(MessageWithEmoji);
        messageRepository.save(msg);

        System.out.println(sender);
        System.out.println(msg);
        return msgC;
    }

    @Override
    public Message SendImageMessage(Message message, Long roomId, MultipartFile multipartFile) {
        Room room = roomRepository.findById(roomId).orElse(null);
        if (room != null) {
            message.setDateMessage(LocalDateTime.now());
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            message.setMsgType(MsgType.Image);
            message.setMessage(fileName);
            String uploadDir = "invitation-photos/" + message.getMessageId();
            try {
                FileUpload.saveFile(uploadDir, fileName, multipartFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return messageRepository.save(message);
        }
        return  null;
    }


    @Override
    public List<Message> getAllMessagesForRoom(Long roomId) {

        return messageRepository.getMessagesByOrder(roomId);
    }


    @Override
    public List<Message> getAllMessagesForUser(String userId) {
        return null;
    }

    @Override
    public List<Message> getMessageByMsgType(MsgType type, Long roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);

        if (room != null) {
            return messageRepository.findMessagesByMsgTypeAndRoom(type,room);
        }return null;
    }


    @Override
    public String GetEmoji(String text) {
        Map<String, String> emoticonMap = new HashMap<>();
        emoticonMap.put(":)", "\uD83D\uDE42");
        emoticonMap.put(":(", "\uD83D\uDE41");
        emoticonMap.put(":D", "\uD83D\uDE00");
        emoticonMap.put(":P", "\uD83D\uDE1B");

        for (Map.Entry<String, String> entry : emoticonMap.entrySet()) {
            String pattern = Pattern.quote(entry.getKey()); // escape any special characters
            text = text.replaceAll(pattern, entry.getValue());
        }

        return text;
    }
    /*****
     *
     *     @Override
     *     public ResponseEntity<Message> addTagToMember(Long roomId,String message) {
     *
     *             int atIndex = message.indexOf('@');
     *             if (atIndex == -1) {
     *                 return ResponseEntity.badRequest().build();// not founde
     *             }
     *
     *             String domain = message.substring(atIndex + 1);
     *             String[] parts = domain.split("\\.");
     *           //  String usernameToCall = parts.length > 0 ? parts[parts.length - 1] : "";
     *             String usernameToCall = message.substring(atIndex + 1).split("\\s+")[0];
     *
     *            List<User> members =participantServices.GetListOfMemvers(roomId);
     *            System.out.println(members);
     *         if (members.contains(usernameToCall)) {// when user don't exist
     *             System.out.println("no user with this name");
     *             System.out.println(usernameToCall);
     *             return ResponseEntity.badRequest().build();
     *             }else {
     *
     *             Message msg = new Message();
     *             msg.setMessage(message);
     *             messageRepository.save(msg);
     *             System.out.println("userExist");
     *
     *             System.out.println(message);
     *         }
     *         return ResponseEntity.ok().build();
     *         }
     *
     *
     *
     *
     *     @Override
     *   public Message createMessage(Message message, Long roomId, String sender) {
     *         Room room = roomRepository.findById(roomId).orElse(null);
     *        if (room != null) {
     *            message.setDateMessage(LocalDateTime.now());
     *            message.setRoom(room);
     *            message.setMsgType(MsgType.Text);
     *
     *            User user =userRepository.findById(sender).orElse(null);
     *            message.setSender(user);
     *            String MessageWithEmoji = GetEmoji(message.getMessage());
     *            message.setMessage(MessageWithEmoji);
     *            return messageRepository.save(message);
     *        }
     *     return message;
     *    }
     *     @Override
     *     public String textMsg(Long roomId, String sender, Message msg) {
     *         System.out.println("refdisc : "+roomId+" sender : "+sender+" message : "+msg.getMessage());
     *
     *         Room room = roomRepository.findById(roomId).orElse(null);
     *
     *         room.setWasRead(Boolean.FALSE);
     *         String msgC = msg.getMessage();
     * //        try {
     * //            msgC = tn.esprit.backend.utils.MessagerieUtils.filterbadwords(msgC);
     * //            System.out.println(msgC);
     * //        } catch (FileNotFoundException e) {
     * //            System.out.println("fichier peut pas le lire");
     * //            e.printStackTrace();
     * //
     * //        }
     *         List<String> badWords = fetchBadWords();
     *
     *         boolean containsBadWord = false;
     *         for (String badWord : badWords) {
     *             if (msg.getMessage().toLowerCase().contains(badWord.toLowerCase())) {
     *                 containsBadWord = true;
     *                 break;
     *             }
     *         }
     *
     *         if (containsBadWord) {
     *             String er="***" ;
     *             return er;
     *
     *         }
     *         msg.setMessage(msgC);
     *         User Sender =userRepository.findById(sender).orElse(null);
     *         msg.setSender(Sender);
     *         msg.setMsgType(MsgType.Text);
     *         msg.setDateMessage(LocalDateTime.now());
     *         String MessageWithEmoji = GetEmoji(msg.getMessage());
     *         msg.setMessage(MessageWithEmoji);
     *         messageRepository.save(msg);
     *
     *         System.out.println(sender);
     *         System.out.println(msg);
     *         return msgC;
     *     }*/

/***
    public Message createMessage(Message message, Long roomId, String sender){
 *         Room room = roomRepository.findById(roomId).orElse(null);
           User user =userRepository.findById(sender).orElse(null);
 *          if (room != null) {
 *
 *      *            message.setDateMessage(LocalDateTime.now());
 *      *            message.setRoom(room);
 *      *            message.setMsgType(MsgType.Text);
 *      *            message.setSender(user);
 *
 *      // test pour les tag
 *      
 *                    int atIndex = message.indexOf('@');
 *      *             if (atIndex == -1) {
 *      *                 return ResponseEntity.badRequest().build();// not founde
 *      *             }
 *      *
 *      *             String domain = message.substring(atIndex + 1);
 *      *             String[] parts = domain.split("\\.");
 *      *           //  String usernameToCall = parts.length > 0 ? parts[parts.length - 1] : "";
 *      *             String usernameToCall = message.substring(atIndex + 1).split("\\s+")[0];
 *      *
 *      *            List<User> members =participantServices.GetListOfMemvers(roomId);
 *      *            System.out.println(members);
 *      *         if (members.contains(usernameToCall)) {// when user don't exist
 *      *             System.out.println("no user with this name");
 *      *             System.out.println(usernameToCall);
 *      *             return ResponseEntity.badRequest().build();
 *      *             }else {
 *      *
 *      *             Message msg = new Message();
 *      *             msg.setMessage(message);
 *      *             messageRepository.save(msg);
 *      *             System.out.println("userExist");
 *      *
 *      *             System.out.println(message);
 *      *         }
 *      //test bad words
 *      //test emoji
 *
                 }
 }
*/
}

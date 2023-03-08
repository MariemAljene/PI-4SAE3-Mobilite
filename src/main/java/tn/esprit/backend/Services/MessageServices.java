package tn.esprit.backend.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.backend.model.Message;
import tn.esprit.backend.model.MsgType;
import tn.esprit.backend.model.React;
import tn.esprit.backend.model.TypeReact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public interface MessageServices {

    public Message createMessage(Message message,Long roomId, String sender);
    public Message SendImageMessage(Message message, Long roomId, MultipartFile multipartFile);

    public List<Message> getAllMessagesForRoom(Long roomId);
    //public List<Message> getMessagesByOrder(Long roomId);
    public  List<Message> getAllMessagesForUser(String userId);
    public List<Message> getMessageByMsgType(MsgType type,Long roomId);

    public String GetEmoji(String text) ;

    public String textMsg(Long roomId, String sender, Message msg);
    public List<String> fetchBadWords();
    public ResponseEntity<Message> addTagToMember(Long roomId,String message);
}

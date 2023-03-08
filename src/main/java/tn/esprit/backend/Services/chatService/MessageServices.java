package tn.esprit.backend.Services.chatService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.backend.model.chatModel.Message;
import tn.esprit.backend.model.chatModel.MsgType;

import java.util.List;

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

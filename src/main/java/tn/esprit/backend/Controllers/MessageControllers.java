package tn.esprit.backend.Controllers;


import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.backend.Repository.MessageRepository;
import tn.esprit.backend.Repository.UserRepository;
import tn.esprit.backend.Services.MessageServices;
import tn.esprit.backend.model.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("chat/M")
public class MessageControllers {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    MessageServices messageServices;
    @PostMapping("/SendTextMsg/{roomId}/{sender}")
    public Message createMessage(@Valid @RequestBody Message message, @PathVariable Long roomId,@PathVariable String sender){
        return messageServices.createMessage(message,roomId, sender);
    }
    @PostMapping("/SendImageMessage/{roomId}/{sender}")
    public Message SendImageMessage(@PathVariable Long roomId, @PathVariable String sender,
                                    @RequestParam String messagecontent, @RequestParam("image") MultipartFile multipartFile) {
        Message message = new Message();

        User s =userRepository.findById(sender).orElse(null);
        message.setSender(s);
        message.setMessage(messagecontent);
        return messageServices.SendImageMessage(message, roomId, multipartFile);
    }
    @PostMapping("/AddMsg/{roomId}/{sender}")
    public String SendMessage(@PathVariable Long roomId,@PathVariable String sender,@RequestBody Message message) {
        return messageServices.textMsg(roomId,sender,message);
    }

    @GetMapping("/GetConversation/{roomId}")
    public List<Message> getAllMessagesForRoom(@PathVariable Long roomId){
        return messageServices.getAllMessagesForRoom(roomId);
    }

    @GetMapping("/GetUserMsg/{id}")
    public  List<Message> getAllMessagesForUser(String userId){
        return messageServices.getAllMessagesForUser(userId);
    }


    @GetMapping("/GetMsgByType/{roomId}/{type}")
    public List<Message> getMessageByMsgType(@PathVariable MsgType type, @PathVariable Long roomId){
        return messageServices.getMessageByMsgType(type,roomId);
    }

    @PostMapping("/addMessageWithTag/{roomId}")
    public ResponseEntity<Message> addTagToMember(@PathVariable Long roomId,@RequestBody String message){
        return messageServices.addTagToMember(roomId,message);
    }
}

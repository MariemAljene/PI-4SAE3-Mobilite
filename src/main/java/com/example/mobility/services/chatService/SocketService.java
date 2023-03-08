package tn.esprit.backend.Services.chatService;


import com.corundumstudio.socketio.SocketIOClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.backend.model.chatModel.Message;
import tn.esprit.backend.model.chatModel.MessageType;

@Service
@Slf4j
public class SocketService implements  SocketServiceI {
    public void sendMessage(String roomA, String eventName, SocketIOClient senderClient, String message) {
        for (
                SocketIOClient client : senderClient.getNamespace().getRoomOperations(roomA).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent(eventName,new Message(MessageType.SERVER,message));
            }
        }
    }
}

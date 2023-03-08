package tn.esprit.backend.Config.chatConfig;


import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tn.esprit.backend.Repository.ChatRepository.RoomRepository;
import tn.esprit.backend.Services.chatService.SocketService;
import tn.esprit.backend.model.chatModel.Message;

@Slf4j
@Component
public class SocketModule {
    private final RoomRepository roomRepository;
    private final SocketIOServer server;
    private final SocketService socketService;


    public SocketModule(SocketIOServer server, SocketService socketService,
                        RoomRepository roomRepository) {
        this.server = server;
        this.socketService = socketService;
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", Message.class, onChatReceived());

        this.roomRepository = roomRepository;
    }


    private DataListener<Message> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            log.info(data.toString());
            socketService.sendMessage(data.getRoomA(),"get_message", senderClient, data.getMessage());
        };
    }


   private ConnectListener onConnected() {
    return (client) -> {

        String roomA = client.getHandshakeData().getSingleUrlParam("roomA");
        client.joinRoom(roomA);
        log.info("Socket ID[{}]  Connected to socket", client.getSessionId().toString());
    };
}
    private DisconnectListener onDisconnected() {
        return client -> {
            log.info("Client[{}] - Disconnected from socket", client.getSessionId().toString());
        };
    }
}

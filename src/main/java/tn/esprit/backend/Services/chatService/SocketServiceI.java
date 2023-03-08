package tn.esprit.backend.Services.chatService;


import com.corundumstudio.socketio.SocketIOClient;

public interface SocketServiceI {
    public void sendMessage(String room, String eventName, SocketIOClient senderClient, String message) ;

    }

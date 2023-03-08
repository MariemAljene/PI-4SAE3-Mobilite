package tn.esprit.backend.Services.chatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.backend.Repository.ChatRepository.MessageRepository;
import tn.esprit.backend.Repository.ChatRepository.ReactRepository;
import tn.esprit.backend.model.chatModel.Message;
import tn.esprit.backend.model.chatModel.React;
import tn.esprit.backend.model.chatModel.TypeReact;

import java.util.List;

@Service
public class ReactService implements ReactServiceImp{

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ReactRepository reactRepository;

    @Override
    public React AddReact(React react, Long messagId) {
        Message msg =messageRepository.findById(messagId).orElse(null);
        react.setMessage(msg);
        return reactRepository.save(react);
    }

    @Override
    public React updateReact(React react, Long messagId) {
        return reactRepository.save(react);
    }

    @Override
    public void DeleteReact(Long idReact, Long messagId) {
        React react= reactRepository.findById(idReact).orElse(null);
        reactRepository.delete(react);
    }

    @Override
    public List<React> getMessageReacts(Long messageId) {
        Message message = messageRepository.findById(messageId).orElse(null);
        List<React> reacts = reactRepository.findReactByMessage(message);
        return  reacts;
    }

    @Override
    public List<React> getReactsByTypeReact(Long messageId, TypeReact typeReact) {
        Message message = messageRepository.findById(messageId).orElse(null);

        List<React> reacts = reactRepository.findReactByMessageAndTypeReact(message,typeReact);

        return reacts;
    }

}



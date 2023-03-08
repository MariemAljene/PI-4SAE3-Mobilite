package tn.esprit.backend.Services.chatService;

import tn.esprit.backend.model.chatModel.React;
import tn.esprit.backend.model.chatModel.TypeReact;

import java.util.List;

public interface ReactServiceImp {

        public React AddReact(React react, Long messagId);
        public React updateReact(React react, Long messagId);
        public void DeleteReact(Long idReact ,Long messagId);
        public  List<React> getMessageReacts(Long messageId);
        public List<React> getReactsByTypeReact(Long messageId, TypeReact typeReact);

}

package tn.esprit.backend.Services;

import org.springframework.http.ResponseEntity;
import tn.esprit.backend.model.Message;
import tn.esprit.backend.model.MsgType;
import tn.esprit.backend.model.React;
import tn.esprit.backend.model.TypeReact;

import java.util.List;

public interface ReactServiceImp {

        public React AddReact(React react, Long messagId);
        public React updateReact(React react, Long messagId);
        public void DeleteReact(Long idReact ,Long messagId);
        public  List<React> getMessageReacts(Long messageId);
        public List<React> getReactsByTypeReact(Long messageId, TypeReact typeReact);

}

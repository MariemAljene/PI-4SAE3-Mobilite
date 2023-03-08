package tn.esprit.backend.Repository.ChatRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.backend.model.chatModel.Message;
import tn.esprit.backend.model.chatModel.React;
import tn.esprit.backend.model.chatModel.TypeReact;

import java.util.List;


@Repository
public interface ReactRepository extends JpaRepository<React, Long> {
    List<React> findReactByMessage(Message message);
    List<React> findReactByMessageAndTypeReact(Message message, TypeReact typeReact);
}
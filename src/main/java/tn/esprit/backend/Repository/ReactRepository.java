package tn.esprit.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.backend.model.Message;
import tn.esprit.backend.model.React;
import tn.esprit.backend.model.TypeReact;

import java.util.List;


@Repository
public interface ReactRepository extends JpaRepository<React, Long> {
    List<React> findReactByMessage(Message message);
    List<React> findReactByMessageAndTypeReact(Message message, TypeReact typeReact);
}
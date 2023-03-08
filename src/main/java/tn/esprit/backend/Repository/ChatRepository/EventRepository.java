package tn.esprit.backend.Repository.ChatRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.backend.model.chatModel.Event;
import tn.esprit.backend.model.chatModel.Room;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findEventByRoom(Room room);

}
package tn.esprit.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.backend.model.Event;
import tn.esprit.backend.model.Room;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findEventByRoom(Room room);

}
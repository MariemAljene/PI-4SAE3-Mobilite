package tn.esprit.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.backend.model.Participant;
import tn.esprit.backend.model.Room;
import tn.esprit.backend.model.User;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    List<Participant> findParticipantByRoom(Room room);
   // Participant findParticipantByParticipantId(String participantId);
    Participant findParticipantByRoomAndParticipant(Room room,User participant);
}
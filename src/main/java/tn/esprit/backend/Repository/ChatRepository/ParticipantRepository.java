package tn.esprit.backend.Repository.ChatRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.backend.model.chatModel.Participant;
import tn.esprit.backend.model.chatModel.Room;
import tn.esprit.backend.model.User;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    List<Participant> findParticipantByRoom(Room room);
   // Participant findParticipantByParticipantId(String participantId);
    Participant findParticipantByRoomAndParticipant(Room room,User participant);
}
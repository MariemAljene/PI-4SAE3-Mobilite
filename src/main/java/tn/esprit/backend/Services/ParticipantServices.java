package tn.esprit.backend.Services;

import org.springframework.web.bind.annotation.RequestBody;
import tn.esprit.backend.model.Participant;
import tn.esprit.backend.model.Room;
import tn.esprit.backend.model.User;

import java.util.List;

public interface ParticipantServices {
    public void addParticipationAndAssignRoomAndUser(Long roomId, String userId);
    public void DeleteParticipation(Participant p,Long roomId,String userId);

    public List<User> GetListOfMemvers(Long roomId);
    public  void ajouterEtAffecterRoomUserss(Room room ,List<Long> partipantid);
    public Room createRoom(String userName, Room room) ;
    public Participant requestParticipation(String userName, Long roomId);
    public void UserbanUser(Long roomId,String userB, String userC) ;

    }

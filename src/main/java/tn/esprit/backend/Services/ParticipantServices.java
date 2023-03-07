package tn.esprit.backend.Services;

import org.springframework.web.bind.annotation.RequestBody;
import tn.esprit.backend.model.Participant;
import tn.esprit.backend.model.Room;
import tn.esprit.backend.model.User;

import java.util.List;

public interface ParticipantServices {
    public void addParticipationAndAssignRoomAndUser( String roomId, String userId);
    public void DeleteParticipation(Participant p,String roomId,String userId);

    public List<User> GetListOfMemvers(String roomId);
    public  void ajouterEtAffecterRoomUserss(Room room ,List<Long> partipantid);

    public Room createRoom(String userName, Room room) ;
    public Participant requestParticipation(String userName, String roomId);
    public void UserbanUser(String roomId,String userB, String userC) ;

    }

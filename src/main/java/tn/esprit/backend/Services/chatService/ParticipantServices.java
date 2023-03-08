package tn.esprit.backend.Services.chatService;

import tn.esprit.backend.model.chatModel.Participant;
import tn.esprit.backend.model.chatModel.Room;
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

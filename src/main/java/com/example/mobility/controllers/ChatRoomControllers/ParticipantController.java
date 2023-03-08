package tn.esprit.backend.Controllers.ChatRoomControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.backend.Services.chatService.ParticipantServices;
import tn.esprit.backend.model.chatModel.Participant;
import tn.esprit.backend.model.chatModel.Room;
import tn.esprit.backend.model.User;

import java.util.List;

@RestController
@RequestMapping("chat")

public class ParticipantController {

   @Autowired
    ParticipantServices participantServices;

    @PostMapping("/createRoomAndAssignCreator/{userName}")
    public Room createRoomAndAssignCreator(@PathVariable String userName, @RequestBody Room room) {
        return participantServices.createRoom(userName,room);
    }
    @PostMapping("/assignRoomToUser/{roomId}/{userId}")
    public void addParticipationAndAssignRoomAndUser(@PathVariable Long roomId, @PathVariable String userId){
        participantServices.addParticipationAndAssignRoomAndUser(roomId,userId);
    }
    @DeleteMapping("/deleteRoom/{p}/{roomId}/{userId}")
    public void DeleteParticipation(@PathVariable Participant p, @PathVariable Long roomId, @PathVariable String userId){
        participantServices.DeleteParticipation(p,roomId,userId);
    }
    @PostMapping("requestParticipation/{userName}/{roomId}")
    public Participant requestParticipation(@PathVariable String userName,@PathVariable Long roomId){
        return participantServices.requestParticipation(userName,roomId);
    }
   @GetMapping("GetListMembeers")
    public List<User> GetListOfMemvers(@PathVariable Long roomId){
        return  participantServices.GetListOfMemvers(roomId);
   }
    @PostMapping("ajouterEtAffecterRoomUserss")
    public  void ajouterEtAffecterRoomUserss(@RequestBody Room room ,@RequestBody List<Long> partipant){
        participantServices.ajouterEtAffecterRoomUserss(room,partipant);
    }
    @PostMapping("UserbanUser/{roomId}/{userB}/{userC}")
    public void UserbanUser(@PathVariable Long roomId,@PathVariable String userB,@PathVariable String userC) {
        participantServices.UserbanUser(roomId,userB,userC);
    }

}

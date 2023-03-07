package tn.esprit.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.backend.Repository.ParticipantRepository;
import tn.esprit.backend.Services.ParticipantServices;
import tn.esprit.backend.model.Participant;
import tn.esprit.backend.model.Room;

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
    public void addParticipationAndAssignRoomAndUser(@PathVariable String roomId, @PathVariable String userId){
        participantServices.addParticipationAndAssignRoomAndUser(roomId,userId);
    }
    @DeleteMapping("/deleteRoom/{p}/{roomId}/{userId}")
    public void DeleteParticipation(@PathVariable Participant p, @PathVariable String roomId, @PathVariable String userId){
        participantServices.DeleteParticipation(p,roomId,userId);
    }
    @PostMapping("requestParticipation/{userName}/{roomId}")
    public Participant requestParticipation(@PathVariable String userName,@PathVariable String roomId){
        return participantServices.requestParticipation(userName,roomId);
    }
}

package tn.esprit.backend.Controllers;


import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.backend.Services.ParticipantServices;
import tn.esprit.backend.Services.RoomServices;
import tn.esprit.backend.model.Event;
import tn.esprit.backend.model.Participant;
import tn.esprit.backend.model.Room;
import tn.esprit.backend.model.User;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("chat")
public class RoomController {
    @Autowired
    RoomServices roomServices;
    @Autowired
    ParticipantServices participantServices;


    @GetMapping("/getAllRooms")
    public List<Room> getAllRooms() {
        return roomServices.getAllRooms();
    }
    @GetMapping("/getRoom/{id}")
    public Room getRoomById(@PathVariable Long id){
        return roomServices.getRoomById(id);
    }
    @PutMapping("/updateRoom/{id}")
    public Room updateRoom(@PathVariable Long id){
        return roomServices.updateRoom(id);
    }
    @DeleteMapping("/deleteRoom/{id}")
    public void deleteRoom(@PathVariable Long id){
        roomServices.deleteRoom(id);
    }

    @GetMapping("/getRoomMembers/{roomId}")
    public List<User> GetListOfMemvers(@PathVariable Long roomId){
        return participantServices.GetListOfMemvers(roomId);
    }

    @PostMapping("RoomMembers/{idUsers}")
    public void ajouterEtAffecterRoomUsers(@RequestBody Room room,@PathVariable List<Long> members){
        participantServices.ajouterEtAffecterRoomUserss(room,members);
    }
    @GetMapping("/searchRoom/{s}")
    public List<Room> searchRoom(@PathVariable String s){
        return  roomServices.searchRoom(s);
    } @GetMapping("/GetRoomOrder")
    public List<Room> getRoomOrderByNewestMessage() {
        return  roomServices.getRoomOrderByNewestMessage();
    }

    @GetMapping("/searchMyRoom/{s}")
    public List<Room> searchMyRooms(@PathVariable String s){
        return  roomServices.searchMyRooms(s);
    }

//

//    @GetMapping("/open-meeting")
//    public void openMeetingLink() throws Exception {
//        roomServices.openMeetingLink();
//    }
//    @GetMapping("/meeting")
//    public String generateMeetingLink() {
//       return roomServices.generateMeetingLink();
//
//    }

    @GetMapping("/open-meeting")
    public void openMeetingLink() {
        roomServices.openMeetingLink();
    }
//    @RequestMapping("/meet/callback")
//    @ResponseBody
//    public String googleMeetCallback(@RequestParam(name = "meetUrl", required = false) String meetUrl) {
//        return meetUrl;
//    }

    @PutMapping("/ban/{roomId}/{userB}/{userC}")
    public void UserbanUser(@PathVariable Long roomId,@PathVariable String userB ,@PathVariable String userC){
        participantServices.UserbanUser(roomId,userB,userC);
    }
  //  @PostMapping("/generate")
//    @GetMapping("/meet/callback")
//    public Map<String, String> generateMeetingLink() {
//        Map<String, String> response = new HashMap<>();
//        response.put("meetUrl", "https://meet.google.com/new");
//        return response;
//    }


    @PostMapping("/meet/callback")
    @ResponseBody
    public String googleMeetCallback(@RequestParam(name = "meetUrl") String meetUrl) {
        // Do something with the meetUrl, such as storing it in a database
        return "Meet URL received: " + meetUrl;
    }
  //  @PostMapping("/creatRoom")
//    public Room createRoom (@Valid @RequestBody  Room room){
//        //problem
//         return roomServices.createRoom(room);
//    }
}

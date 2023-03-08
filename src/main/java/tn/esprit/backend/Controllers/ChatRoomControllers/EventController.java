package tn.esprit.backend.Controllers.ChatRoomControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.backend.Services.chatService.EventService;
import tn.esprit.backend.model.chatModel.Event;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("chat")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping("/CreatEvent/{roomId}")
    public ResponseEntity<String> createEvent(@PathVariable Long roomId, @Valid @RequestBody Event event) {
        return  eventService.createEvent(roomId,event);
    }
    @GetMapping("/GetEventOfRoom/{roomId}")
    public Event getEventsForRoom(@PathVariable Long roomId){
        return eventService.getEventsForRoom(roomId);
    }
    @GetMapping("/GetEvent/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }
    @PutMapping("/updateEvent/{id}")
    public Event updateEvent( @PathVariable Long id,@RequestBody Event event){
        return  eventService.updateEvent(id, event);
    }
    @DeleteMapping("/deleteEvent/{id}")
    public void deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
    }

    @GetMapping("/getEvents")
    public List<Event> getAllEvent(){
        return eventService.getAllEvent();
    }
}

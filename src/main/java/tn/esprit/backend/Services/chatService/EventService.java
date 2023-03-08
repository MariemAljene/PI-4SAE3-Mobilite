package tn.esprit.backend.Services.chatService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.esprit.backend.Repository.ChatRepository.EventRepository;
import tn.esprit.backend.Repository.ChatRepository.RoomRepository;
import tn.esprit.backend.model.chatModel.Event;
import tn.esprit.backend.model.chatModel.Room;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService  implements EventServiceImp{

    @Autowired
    EventRepository eventRepository;
    @Autowired
    RoomRepository roomRepository;


    @Override
    public ResponseEntity<String> createEvent(Long roomId, Event event) {
        Room room = roomRepository.findById(roomId).orElse(null);
        Event eventExist = eventRepository.findEventByRoom(room);
        if (eventExist != null) {
            System.out.println("already exist an event");
            return ResponseEntity.badRequest().body("event is already exist");
        } else {
            event.setRoom(room);
            eventRepository.save(event);
            return ResponseEntity.ok("Event is Added ");
        }
    }
    @Override
    public Event getEventsForRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElse(null);
        return eventRepository.findEventByRoom(room);
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        Event e = eventRepository.findById(id).orElse(null);
        if (e != null) {
            e.setName(event.getName());
            e.setDate(event.getDate());
            return eventRepository.save(e);
        }
        return null;
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        eventRepository.delete(event);
    }

    @Override
    public void deleteExpiredEvents() {
        List<Event> events = eventRepository.findAll();
        LocalDateTime today = LocalDateTime.now();

        for (Event event : events) {
            if (event.getDate().isBefore(today)) {
                //   eventRepository.delete(event);
                Long id = event.getEventId();
                eventRepository.deleteById(id);

                System.out.println("event is deleted");
                System.out.println(event.getDate());
                System.out.println(today);
            }
        }
    }

    @Override
    public List<Event> getAllEvent() {
        return eventRepository.findAll();
    }
}

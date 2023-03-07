package tn.esprit.backend.Services;

import org.springframework.http.ResponseEntity;
import tn.esprit.backend.model.Event;

import java.util.List;

public interface EventServiceImp {

    //Event
    public ResponseEntity<String> createEvent(String roomId, Event event);
    public Event getEventsForRoom(String roomId) ;
    public Event getEventById(Long id) ;
    public Event updateEvent(Long id, Event event);
    public void deleteEvent(Long id);
    public void deleteExpiredEvents();
    public List<Event> getAllEvent();
}

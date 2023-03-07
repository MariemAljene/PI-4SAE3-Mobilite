package tn.esprit.backend.Services;

import org.springframework.http.ResponseEntity;
import tn.esprit.backend.model.Event;
import tn.esprit.backend.model.Room;

import java.util.List;

public interface RoomServices {

    public Room createRoom(Room room);
    public List<Room> getAllRooms();
    public Room getRoomById(String id);
    public Room updateRoom(String id);
    public void deleteRoom(String id);
    public List<Room> searchRoom(String s);
    public List<Room> searchMyRooms(String s);
    //  search in only my rooms privte and public /!\

    public List<Room> getRoomOrderByNewestMessage();
//
//    //Event
//    public ResponseEntity<String> createEvent(String roomId, Event event);
//    public Event getEventsForRoom(String roomId) ;
//    public Event getEventById(Long id) ;
//    public Event updateEvent(Long id, Event event);
//    public void deleteEvent(Long id);
//    public void deleteExpiredEvents();
//    public List<Event> getAllEvent();
    public void openMeetingLink();
    public String generateMeetingLink() ;
}

package tn.esprit.backend.Services;

import org.springframework.http.ResponseEntity;
import tn.esprit.backend.model.Event;
import tn.esprit.backend.model.Room;

import java.util.List;

public interface RoomServices {

    public Room createRoom(Room room);
    public List<Room> getAllRooms();
    public Room getRoomById(Long id);
    public Room updateRoom(Long id);
    public void deleteRoom(Long id);
    public List<Room> searchRoom(String s);
    public List<Room> searchMyRooms(String s);

    public List<Room> getRoomOrderByNewestMessage();

    public void openMeetingLink();
    public String generateMeetingLink() ;
}

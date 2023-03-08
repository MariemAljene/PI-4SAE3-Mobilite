package tn.esprit.backend.Services.chatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.backend.Repository.ChatRepository.EventRepository;
import tn.esprit.backend.Repository.ChatRepository.RoomRepository;
import tn.esprit.backend.model.chatModel.Room;

import java.util.List;

@Service
public class RoomServicesImp implements  RoomServices{
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    MessageServices messageServices;
    @Override
    public Room createRoom( Room room) {
        room.setAccess(Boolean.FALSE);
        String roomWithEmoji = messageServices.GetEmoji(room.getName());
        room.setName(roomWithEmoji);
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        Room room =roomRepository.findById(id).orElse(null);
        return room;
    }

    @Override
    public Room updateRoom(Long id) {
        Room r =  roomRepository.findById(id).orElse(null);
        if(r !=null) {
            return roomRepository.save(r);
        }
        return null;
    }


    @Override
    public void deleteRoom(Long id) {
        Room r = roomRepository.findById(id).orElse(null);
        if(r !=null) {
            roomRepository.delete(r);
        }
    }

    @Override
    public List<Room> searchRoom(String s) {
        return  roomRepository.findRoomByAccessAndNameLike(Boolean.TRUE,"%" + s + "%");
    }// filtre has to check the public attribute /!\

    @Override
    public List<Room> searchMyRooms(String s) {
        return  roomRepository.findRoomByNameLike("%" + s + "%");
    }

    @Override
    public List<Room> getRoomOrderByNewestMessage() {
        return roomRepository.findAllOrderByNewestMessage();
    }



    @Override
    public String generateMeetingLink() {

        return "https://meet.google.com/new" ;
       // return "{"meetUrl":"" + meetUrl + ""}";
    }

    @Override
    public void openMeetingLink() {
        try {
            String meetLink = generateMeetingLink();
            String os = System.getProperty("os.name").toLowerCase();
            Runtime rt = Runtime.getRuntime();

            if (os.contains("win")) {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + meetLink);
            } else if (os.contains("mac")) {
                rt.exec("open " + meetLink);
            } else if (os.contains("nix") || os.contains("nux")) {
                rt.exec("xdg-open " + meetLink);
            }
        } catch (Exception e) {
            // Handle the exception here, e.g. log it or show an error message to the user
            e.printStackTrace();
        }
    }
}










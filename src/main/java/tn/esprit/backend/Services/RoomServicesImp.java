package tn.esprit.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tn.esprit.backend.Repository.EventRepository;
import tn.esprit.backend.Repository.RoomRepository;
import tn.esprit.backend.model.Event;
import tn.esprit.backend.model.Room;

import java.awt.*;
import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.awt.*;
import java.net.URI;
import org.apache.commons.lang3.RandomStringUtils;

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
    public Room getRoomById(String id) {
        Room room =roomRepository.findById(id).orElse(null);
        return room;
    }

    @Override
    public Room updateRoom(String id) {
        Room r =  roomRepository.findById(id).orElse(null);
        if(r !=null) {
            return roomRepository.save(r);
        }
        return null;
    }


    @Override
    public void deleteRoom(String id) {
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










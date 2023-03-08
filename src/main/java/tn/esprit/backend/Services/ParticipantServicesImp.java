package tn.esprit.backend.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.backend.Repository.ParticipantRepository;
import tn.esprit.backend.Repository.RoomRepository;
import tn.esprit.backend.Repository.UserRepository;
import tn.esprit.backend.model.Participant;
import tn.esprit.backend.model.ParticipationStatus;
import tn.esprit.backend.model.Room;
import tn.esprit.backend.model.User;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantServicesImp implements ParticipantServices {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageServices messageServices;

    @Override
    public Room createRoom(String userName, Room room) {
        // Create a new room
        room.setAccess(Boolean.FALSE);
        String roomWithEmoji = messageServices.GetEmoji(room.getName());
        room.setName(roomWithEmoji);
        roomRepository.save(room);

        User creator = userRepository.findById(userName).orElse(null);
        // Create a new participation for the creator and add it to the room
        Participant participation = new Participant();
        participation.setRoom(room);
        participation.setParticipant(creator);
        participantRepository.save(participation);

        // Set the creator as the room owner
        room.setOwner(userName);
        roomRepository.save(room);

        return room;
    }

    @Override
    @Transactional
    public Participant requestParticipation(String userName, Long roomId) {
            // Check if the user is already a member of the room
        Room room = roomRepository.findById(roomId).orElse(null);
        User user = userRepository.findById(userName).orElse(null);
            if (room.getMembers().stream().anyMatch(p -> p.getParticipant().equals(user))) {
                throw new IllegalArgumentException("User is already a member of the room");
            }

            // Check if the room has reached its capacity
            if (room.getMembers().size() >= room.getCapacity()) {
                throw new IllegalArgumentException("Room has reached its capacity");
            }

            Participant participation = new Participant();
            participation.setParticipant(user);
            participation.setRoom(room);
            participation.setStatus(ParticipationStatus.REQUESTED);
            participantRepository.save(participation);
            return participation;
       // return  null;

    }



    public void addParticipationAndAssignRoomAndUser(Long roomId,String userId){
        User user = userRepository.findById(userId).orElse(null);
        Room room = roomRepository.findById(roomId).orElse(null);
        if(user !=null && room !=null){
            Participant p = new Participant();
            p.setParticipant(user);
            p.setRoom(room);
            p.setAddDate(LocalDate.now());
            participantRepository.save(p);
        }
    }
    public void DeleteParticipation(Participant p,Long roomId,String userId){
            participantRepository.delete(p);
    }


    @Override
    public List<User> GetListOfMemvers(Long roomId) {
        Room room =roomRepository.findById(roomId).orElse(null);
        List<Participant> p= participantRepository.findParticipantByRoom(room);
        List<User>  usersInRoom = new ArrayList<>();
        for (Participant participant : p) {
            User user = participant.getParticipant();
            usersInRoom.add(user);
        }
        return  usersInRoom;
    }

    @Override
    @Transactional
    public void ajouterEtAffecterRoomUserss(Room room, List<Long> members) {
        roomRepository.save(room);
        for (Long member : members){
            Participant participant = participantRepository.findById(member).orElse(null);
            room.getMembers().add(participant);
        }
    }

    @Override
    public void UserbanUser(Long roomId,String userB ,String userC) {

        // Check that the user requesting the ban is the owner of the room
         Room room = roomRepository.findById(roomId).orElse(null);
          User ownerR = userRepository.findById(room.getOwner()).orElse(null);
          User userConect= userRepository.findById(userC).orElse(null);
          if (!ownerR.equals(userConect)){
           //   throw new IllegalArgumentException("Only the owner of the room can ban users");
              System.out.println(ownerR);
              System.out.println(userB);
          }
          // Find the participation of the user to be banned
        User b= userRepository.findById(userB).orElse(null);
        Participant p= participantRepository.findParticipantByRoomAndParticipant(room,b);
        System.out.println(p);
        System.out.println(b);
        // Update the status of the participation to BANNED
        p.setStatus(ParticipationStatus.BANNED);
        participantRepository.save(p);
    }


}

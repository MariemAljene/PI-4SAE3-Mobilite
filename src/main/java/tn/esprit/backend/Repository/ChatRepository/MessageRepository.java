package tn.esprit.backend.Repository.ChatRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.backend.model.chatModel.Message;
import tn.esprit.backend.model.chatModel.MsgType;
import tn.esprit.backend.model.chatModel.Room;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByRoom(Room room);
    @Query("SELECT m FROM Room r JOIN r.messages m WHERE r.RoomId = :roomId ORDER BY m.DateMessage")
    List<Message> getMessagesByOrder(@Param("roomId") Long roomId);
    List<Message> findMessagesByMsgType( MsgType type);
    List<Message> findMessagesByMsgTypeAndRoom(MsgType msgType,Room room);



}
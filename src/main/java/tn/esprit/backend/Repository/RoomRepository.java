package tn.esprit.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.backend.model.Room;

import java.util.List;


@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    Room findRoomByName(String r);
    //List<Room> findRoomByRpIsTrue();
    List<Room> findRoomByNameLike(String name);
    List<Room> findRoomByAccessAndNameLike(Boolean p,String s);
    @Query("SELECT cr FROM Room cr JOIN cr.messages m ORDER BY m.DateMessage DESC")
    List<Room> findAllOrderByNewestMessage();
}
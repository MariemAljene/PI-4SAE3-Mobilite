package tn.esprit.spring.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RoomId;
    private String name;



    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    public  List<Participant> members;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    public List<Message> messages;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    public Event event;
}

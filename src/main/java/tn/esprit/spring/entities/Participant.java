package tn.esprit.spring.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ParticipantId;
    private Long RoomId;
    private String UserId;

    @ManyToOne
    public User participant;

    @ManyToOne
    public Room  room;
}

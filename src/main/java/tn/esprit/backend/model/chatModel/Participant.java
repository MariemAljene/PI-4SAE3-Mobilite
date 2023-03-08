package tn.esprit.backend.model.chatModel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.backend.model.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ParticipantId;
    //@Temporal(TemporalType.DATE)
    private LocalDate AddDate;
    @Enumerated(EnumType.STRING)
    private ParticipationStatus status;
    @JsonIgnore
    @ManyToOne
    public User participant;

    @JsonIgnore
    @ManyToOne
    public Room  room;
}

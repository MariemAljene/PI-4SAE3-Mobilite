package tn.esprit.spring.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MessageId;
    private String message;
    @Temporal(TemporalType.DATE)
    private Date DateMessage;
    private boolean wasRead;
    @Enumerated(EnumType.STRING)
    private MsgType msgType;


    @ManyToOne
    public Room room;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    public List<React> reacts;


}

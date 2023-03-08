package tn.esprit.backend.model.chatModel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import tn.esprit.backend.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MessageId;
    @NotBlank(message = "name is required")
    private String message;
    //@Temporal(TemporalType.DATE)
    private LocalDateTime DateMessage;
    //    private boolean wasRead;
    @Enumerated(EnumType.STRING)
    private MsgType msgType;
    @Enumerated(EnumType.STRING)
    private MessageType type;
    private String roomA;


    @JsonIgnore
    @ManyToOne
    public Room room;

    @JsonIgnore
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    public List<React> reacts;

    @JsonIgnore
    @ManyToOne
    public User sender;


    public Message(MessageType server, String message) {

    }
}


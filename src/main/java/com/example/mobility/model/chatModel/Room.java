package tn.esprit.backend.model.chatModel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long RoomId;
    @NotBlank(message = "name is required")
    private String name;
    private Boolean access;//roomprivteorpuboic
    private int capacity;
    private  String Owner;
    private boolean wasRead;
    private String LastSender;



    @JsonIgnore
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    public List<Participant> members;
    @JsonIgnore
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    public List<Message> messages;
    @JsonIgnore
    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    public Event event;





}

package tn.esprit.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
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
    @NotBlank(message = "name is required")
    private String message;
    //@Temporal(TemporalType.DATE)
    private LocalDateTime DateMessage;
//    private boolean wasRead;
    @Enumerated(EnumType.STRING)
    private MsgType msgType;


    @JsonIgnore
    @ManyToOne
    public Room room;

    @JsonIgnore
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    public List<React> reacts;

    @JsonIgnore
    @ManyToOne
    public User sender;
//    @ManyToOne
//    @JoinColumn(name = "sender_id")
//    private User sender;
@ManyToMany(cascade=CascadeType.ALL)
@JsonIgnore

private List<Tag> tag;
}

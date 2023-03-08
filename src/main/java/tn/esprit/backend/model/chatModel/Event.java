package tn.esprit.backend.model.chatModel;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long EventId;
    @NotBlank(message = "name is required")
    private String name;
    //@NotBlank(message = "name is required")
    //@Temporal(TemporalType.TIME)
    private LocalDateTime date;

    @JsonIgnore
    @OneToOne
    public Room room;
}

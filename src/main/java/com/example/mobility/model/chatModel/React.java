package tn.esprit.backend.model.chatModel;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class React {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ReactId;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private TypeReact typeReact;

    @JsonIgnore
    @ManyToOne
    public Message message;
}

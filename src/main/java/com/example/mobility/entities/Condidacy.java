package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Condidacy implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer  Id_Condidacy;

    private  float Score;
    private  String MotivationDescription ;
    private  int Status ;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="student")
    private User student;
    @ManyToOne(cascade = CascadeType.ALL)
    Opportunity Opportunity;
}
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

public class Reclamation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer IdReclam;
    private String Reclam;
    private String Objet;
    private String AdressMail;



    @OneToOne
     SolvReclam solvReclam;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userRec")

    private User userRec;
















}

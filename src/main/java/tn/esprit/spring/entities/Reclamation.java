package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reclamation implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long IdReclam;
    private String Comment;
    private String Objet;
    private String AdressMail;
    private Statu statu;
    private boolean delegue;

    private Date dateReclamation ;



    @JsonIgnore
    @OneToOne
     SolvReclam solvReclam;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    @JsonIgnoreProperties({"reclamations","reclamations_a_treiter"})

    private User ownerUser;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_traiteur")
    @JsonIgnoreProperties({"reclamations_a_traiter","reclamations"})
    private User traiteurUser;




    public Reclamation(String Comment , User ownerUser , User traiteurUser){
        this.Comment=Comment ;
        this.ownerUser=ownerUser;
        this.traiteurUser=traiteurUser;

        this.statu=Statu.valueOf("EN_COURS");
        this.dateReclamation=new Date();
        this.delegue=false;


    }

    public Reclamation(String Comment,String Objet){
        this.Comment=Comment;
        this.Objet=Objet;

        this.statu=Statu.valueOf("EN_COURS");
        this.dateReclamation=new Date();
        this.delegue=false;
    }



















}

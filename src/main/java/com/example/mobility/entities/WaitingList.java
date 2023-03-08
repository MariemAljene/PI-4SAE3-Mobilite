package tn.esprit.spring.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WaitingList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Integer idWaiting;
    private String email;
    private Integer PhoneNumber;
    private Float Paiment;
    @OneToOne
    private Appointement appointement;


}

package com.example.appoitement.entities;

import com.example.appoitement.services.AppointementListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "appointement")
//@EntityListeners(AppointementListener.class)
public class Appointement  implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    private Integer idAppointement;
    private String firstname;
    private String lastname;
    private String email;
    private Integer PhoneNumber;
    // @Temporal(TemporalType.DATE)
    private LocalDate dateDemande;
    // @Temporal(TemporalType.DATE)
    private LocalDate dateRdv;
    private Boolean status ;

    @ToString.Exclude
    @ManyToOne
    private Appointement rdv;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Appointement app;


}

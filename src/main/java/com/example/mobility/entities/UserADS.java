package tn.esprit.spring.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserADS {
    @Id
    private String id_ADS;
    private String Title;
    private LocalDate start_Date;
    private LocalDate End_Date;
    private String UnyName;
     @ManyToOne
     private  User user;

     @OneToOne
    private Opportunity opportunity;





}

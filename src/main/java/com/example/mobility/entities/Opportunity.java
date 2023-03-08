package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Opportunity   implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer  Id_Opportunity;
    private  int    Capacity;
    private LocalDate StarDate ;
    private  LocalDate EndDate;
    private  TypeOpp Type ;
    private  Grade grade ;
    private  Speciality specialite ;
    private  float needs ;
    private  String Description ;
    private  String Title;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="Partner")
    private User Partner;
}
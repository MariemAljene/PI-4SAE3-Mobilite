package tn.esprit.spring.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@Setter
@AllArgsConstructor
public class Stat implements Serializable {
    private String Gender;
    private String mois ;
    private String grade ;

    private int somReclamation ;

}

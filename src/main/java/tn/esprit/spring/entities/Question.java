package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer IdQuestion ;
    private String Question ;
    private Type Type ;

   



    @JsonIgnore
    @ManyToOne
    @Nullable
    @JoinColumn(name = "userQ")

    private User userQ;


    @JsonIgnore
    @OneToOne(mappedBy = "question",cascade = CascadeType.REMOVE)
    private Reponse reponse;


    @JsonIgnore
    @OneToMany(mappedBy = "question")
    private List<Notification> notifications = new ArrayList<>();






}

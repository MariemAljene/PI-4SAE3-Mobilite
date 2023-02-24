package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private  float Moyenne_1year;
    private  float Moyenne_2year;
    private  float Moyenne_3year;
    private float  score;
    private boolean attempted = false;
    private  String MotivationDescription ;
    @Enumerated(EnumType.STRING)

    private  status Status ;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")

    private User user;
@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opportunity_id")
    private Opportunity opportunity;
    @OneToOne(mappedBy = "condidacy", cascade = CascadeType.ALL)
    private QuizAttempt quizAttempt;


}

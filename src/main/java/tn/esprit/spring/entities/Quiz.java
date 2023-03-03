package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Quiz;

    private String title;
    private String description;
    private int Duration;

    @JsonIgnore
    @OneToOne
    private Opportunity opportunity;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "quiz_question",
            joinColumns = {@JoinColumn(name = "quiz_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id")}
    )
    private Set<Question> questions = new HashSet<>();


    private LocalDate startDate;
    private int nbQuestion;
    private LocalDate endDate;

    public Quiz(String title, String description, Opportunity opportunity, List<Question> questions, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.opportunity = opportunity;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

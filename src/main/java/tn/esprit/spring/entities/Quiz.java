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
import java.util.List;

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
    @JsonIgnore

    @ManyToOne
    private Opportunity opportunity;
    @JsonIgnore

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions;

    private LocalDate startDate;
    private LocalDate endDate;
    public Quiz(String title, String description, Opportunity opportunity, List<Question> questions, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.opportunity = opportunity;
        this.questions = questions;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

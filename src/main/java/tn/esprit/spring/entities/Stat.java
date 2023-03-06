package tn.esprit.spring.entities;

import javax.persistence.*;

@Entity
@Table(name = "stat")
public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "specialite")
    private String specialite;

    @Column(name = "percentage")
    private double percentage;

    public Stat() {
    }

    public Stat(String specialite, double percentage) {
        this.specialite = specialite;
        this.percentage = percentage;
    }

    // getters and setters
}


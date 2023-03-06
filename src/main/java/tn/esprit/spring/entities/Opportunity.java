package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Opportunity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Opportunity;
    private int Capacity;
    private LocalDate StarDate;
    private LocalDate EndDate;
    @Enumerated(EnumType.STRING)
    private TypeOpp Type;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    @Enumerated(EnumType.STRING)
    private Speciality specialite;
    private float needs;
    private String Description;
    private String Title;
    private float Coef1stYear;
    private float Coef2stYear;
    private float Coef3stYear;
    @Column(length = 1000)
    private String qrContent;
    private byte[] qrCodeImage;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    @OneToOne(mappedBy = "opportunity", cascade = CascadeType.ALL)
    private Quiz quizzesQuiz;
    private double averageRating;
    private int numberOfRatings;

    public void setQrCodeImage(byte[] qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }


}

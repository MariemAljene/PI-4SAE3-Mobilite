package tn.esprit.spring.entities;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryCandidaciesDTO {
    private float average1Year;
    private float average2Year;
    private float average3Year;
    private float score;
    private Grade grade;
    private status status;
    private String opportunityName;
}

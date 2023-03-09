package tn.esprit.spring.entities;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingDTO {
    private Long opportunityId;
    @NotNull
    @Min(1)
    @Max(5)
    private int rating;
}

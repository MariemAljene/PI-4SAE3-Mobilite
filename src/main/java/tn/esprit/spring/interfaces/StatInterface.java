package tn.esprit.spring.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface StatInterface {
    public Map<String, Double> getOpportunitiesPercentageBySpeciality();

    Map<String, Double> getAverageQuizScoreByOpportunityType();
}

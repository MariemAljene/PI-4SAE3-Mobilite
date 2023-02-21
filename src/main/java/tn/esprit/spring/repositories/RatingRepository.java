package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Rating;
@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {
}

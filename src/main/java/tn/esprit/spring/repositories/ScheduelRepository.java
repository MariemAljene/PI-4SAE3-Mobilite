package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Schedule;

public interface ScheduelRepository extends JpaRepository<Schedule ,Integer> {
}

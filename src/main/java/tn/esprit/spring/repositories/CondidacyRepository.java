package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Condidacy;
import tn.esprit.spring.entities.User;

import java.util.List;

@Repository
public interface CondidacyRepository extends JpaRepository  <Condidacy ,Integer> {
    List<Condidacy> findAllByUser(User user);
}

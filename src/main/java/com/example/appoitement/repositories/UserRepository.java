package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}

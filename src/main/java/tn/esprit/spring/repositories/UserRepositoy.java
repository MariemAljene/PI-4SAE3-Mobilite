package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Opportunity;
import tn.esprit.spring.entities.User;

import java.util.List;

@Repository

public interface UserRepositoy extends JpaRepository<User, String> {
    User findByUserName(String userName);


}

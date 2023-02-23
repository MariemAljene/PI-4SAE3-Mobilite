package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.User;

public interface UserRepositoy extends JpaRepository<User,String> {
}

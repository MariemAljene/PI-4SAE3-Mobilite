package tn.esprit.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.backend.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
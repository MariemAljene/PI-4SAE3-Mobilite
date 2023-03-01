package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
   // public Optional<User> findByName(String name);
  //  User findByEmail(String Email);
  User findByuserName(String userName);
   @Query("SELECT u FROM User u WHERE u.Email = ?1")
   User findByEmail(String email);
}

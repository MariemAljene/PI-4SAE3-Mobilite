package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;

import java.util.List;

public interface UserRepositoy extends JpaRepository<User,String> {


    public User findByUserName(String username);






}

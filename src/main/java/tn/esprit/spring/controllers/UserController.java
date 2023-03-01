package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.PasswordResetTokenRepository;
import tn.esprit.spring.services.User.UserNotFoundException;
import tn.esprit.spring.services.User.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerNewUser")
    public User createUser(@RequestBody User user) {
        User savedUser = userService.registerNewUser(user);
        String to = user.getEmail();
        String subject = "Account Created";
        String text = "Your account has been created successfully.";
        userService.sendEmail(to, subject, text);
        return savedUser;
    }

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

  /*  @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }*/

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/users"})
    @PreAuthorize("hasRole('Admin')")
    public List<User> getAll(){
        return userService.getAll();
    }
    @GetMapping({"/user/{userName}"})
    public User findOne(@PathVariable String userName){
        return userService.findOne(userName);
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    @DeleteMapping ({"/delete/{userName}"})
    @PreAuthorize("hasRole('Admin')")
    public void delete(@PathVariable String userName){
        userService.delete(userName);
    }

    @PutMapping  ({"/update"})
    @PreAuthorize("hasRole('User')")
    public void update(@RequestBody User user){
        userService.update(user);
    }
    @GetMapping("/count")
    @PreAuthorize("hasRole('Admin')")
    public long count(){return userService.count();}
    @GetMapping("/countoperateur")
    @PreAuthorize("hasRole('Admin')")
    public long countoperateur(){return userService.countoperateur();}

    @PutMapping("/change-password/{userName}/{newPassword}")
    public ResponseEntity<Void> changePassword(@PathVariable String userName, @PathVariable String newPassword) {
        userService.changePassword(userName, newPassword);
        return ResponseEntity.ok().build();
    }
   @PostMapping("/forgot-password")
   @PermitAll
   public ResponseEntity<?> forgotPassword(@RequestParam("Email") String Email) {
       userService.generatePasswordResetToken(Email);
       return ResponseEntity.ok().build();
   }



}
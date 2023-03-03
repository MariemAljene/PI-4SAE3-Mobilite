package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.VerificationToken;
import tn.esprit.spring.services.User.EmailService;
import tn.esprit.spring.services.User.UserService;
import tn.esprit.spring.services.User.VerificationTokenService;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private VerificationTokenService verificationTokenService;

    @PostMapping("/registerNewUser")
    public User createUser(@RequestBody User user) {
        User savedUser = userService.registerNewUser(user);
      //  String to = user.getEmail();
       // String subject = "Account Created";
       // String text = "Your account has been created successfully.";
       // userService.sendEmail(to, subject, text);
        VerificationToken verificationToken = verificationTokenService.createVerificationToken(user); // création du jeton de vérification
        verificationTokenService.saveVerificationToken(verificationToken);
        return savedUser;
    }
    @PutMapping("/activate/{verificationToken}")
    public ResponseEntity activateAccount(@PathVariable String verificationToken) {
        User user = userService.activateUser(verificationToken);
        if (user != null) {
             String to = user.getEmail();
             String subject = "Account Created";
             String text = "Your account has been created successfully.";
            emailService.sendEmail(to, subject, text);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
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

    @GetMapping("/unverified-users")
    @PreAuthorize("hasRole('Admin')")
    public List<User> getUnverifiedUsers() {
      return   userService.getUnverifiedUsers();
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

  @PutMapping("/VerifUser/{userName}")
  @PreAuthorize("hasRole('Admin')")
  public void VerifUser(@PathVariable String userName) {

      userService.ISVerified(userName);
  }



}
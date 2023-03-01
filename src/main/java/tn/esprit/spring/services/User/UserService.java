package tn.esprit.spring.services.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.PasswordResetToken;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.PasswordResetTokenRepository;
import tn.esprit.spring.repositories.RoleRepository;
import tn.esprit.spring.repositories.UserRepository;



import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userDao;

    @Autowired
    private RoleRepository roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender userMailSender;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;







    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        Role partnerRole = new Role();
        partnerRole.setRoleName("Partner");
        partnerRole.setRoleDescription("Default role for newly created record");
        roleDao.save(partnerRole);
    }

    public User registerNewUser(User user) {
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

//////-----------RegistrationEmail----------/////////
        public void sendEmail(String to, String subject, String text) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            userMailSender.send(message);
        }


    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public List<User> getAll(){
        return userDao.findAll();
    }
    public User findOne(String userName){
        return userDao.findById(userName).orElse(null);
    }
    public void delete(String userName){
        User u= userDao.findById(userName).orElse(null);
        u.getRole().clear();
        userDao.delete(u);
    }
    public void update(User user){
        userDao.save(user);
    }

    public long count(){
      long count=userDao.count();
      return count;
    }
    public long countoperateur(){
        long countoperateur=0;
        List<User> users=userDao.findAll();
        for(User user:users) {

        Set<Role> roles=user.getRole();
        Role role= roles.iterator().next();
        String rolename = role.getRoleName();
                if(rolename.equals("User")){
                    countoperateur+=1;
                    }
        }
            return countoperateur;
    }

    public User findByEmail(String Email) {
        return userDao.findByEmail(Email);
    }

    @Value("${spring.mail.username}")
    private String fromAddress;
    public void generatePasswordResetToken(String Email) {
        User user = userDao.findByEmail(Email);
        if (user == null) {
            throw new UserNotFoundException("No user found with Email: " + Email);
        }

        String token = generateToken();

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(LocalDateTime.now().plusHours(24));

        passwordResetTokenRepository.save(passwordResetToken);

        String subject = "Password reset request";
        String text = "Please click the following link to reset your password: http://localhost:8081/forgot-password?token=" + token;
        sendEmail(user.getEmail(), subject, text);
    }
    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    public void changePassword(String userName, String newPassword) {
        User user = userDao.findByuserName(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        user.setUserPassword(passwordEncoder.encode(newPassword));
        userDao.save(user);
    }

   /* public void desactivate_Acount(String userId) {
        User user = userDao.findById(userId).get();
        user.setConnected(false);
        user.setDesactivate(true);
        userDao.save(user);
    }


    public void activate_Acount(String userId) {
        User user = userDao.findById(userId).get();
        user.setDesactivate(false);
        user.setLastLoginDate(new Date());
        userDao.save(user);
    }*/


}

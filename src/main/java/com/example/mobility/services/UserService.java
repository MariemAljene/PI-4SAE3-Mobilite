package tn.esprit.spring.services.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.RoleRepository;
import tn.esprit.spring.repositories.UserRepository;


import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class UserService {
    @Autowired
    private UserRepository userDao;

    @Autowired
    private RoleRepository roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


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



//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

    public User registerNewUser(User user) {
        //Role role = roleDao.findById("User").get();
        //Set<Role> userRoles = new HashSet<>();
        //userRoles.add(role);
        //user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
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
   /* @Transactional
    public void assignOperateurToFacture(String userName, Long idFacture) {
        User user = userDao.findById(userName).orElse(null);
        Facture facture = factureServiceImp.findById(idFacture);
        Set<Facture> factures = new HashSet<>();
        factures.add(facture);
        user.setFactures(factures);
        user.getFactures().add(facture);
    }*/
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
    public long countadmin(){
        long countadmin=0;
        List<User> users=userDao.findAll();
        for(User user:users) {

            Set<Role> roles=user.getRole();
            Role role= roles.iterator().next();
            String rolename = role.getRoleName();
            if(rolename.equals("Admin")){
                countadmin+=1;
            }
        }
        return countadmin;
    }

}

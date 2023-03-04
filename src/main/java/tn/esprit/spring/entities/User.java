package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String Email;
    private String Photo;
    private String PhoneNumber;
    private LocalDate Birthdate;
    private String CIN;
    private String UnyName;
    private String Adresse;
    private int Score;
    private String CV;
    private String Gender;
    private String Grade;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }


    @JsonIgnore
   @OneToMany(mappedBy = "userQ")
    private List<Question> questions = new ArrayList<>();



   @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser")
    @JsonIgnoreProperties({"ownerUser"})
    @ToString.Exclude
    private List<Reclamation> reclamations;



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_traiteur")
    @JsonIgnoreProperties({"traiteurUser"})
    @ToString.Exclude
    private List<Reclamation> reclamations_a_traiter;




    public User(){
        reclamations=new ArrayList<>();
        reclamations_a_traiter=new ArrayList<>();
    }
    public User(String userFirstName,String userLastName,String Email){
        this.userFirstName=userFirstName;
        this.userLastName=userLastName;
        this.Email=Email;
        reclamations = new ArrayList<>();
        reclamations_a_traiter =new ArrayList<>();

    }




}

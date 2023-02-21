package tn.esprit.spring.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
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

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Condidacy> candidacies;
    @OneToMany(mappedBy = "Partner", cascade = CascadeType.ALL)
    private List<Opportunity> opportunities;

    @OneToMany( cascade = CascadeType.ALL)
    private  List<Rating> ratingList;
    @OneToMany( cascade = CascadeType.ALL)
    private List<Post> postList;
    @OneToMany( cascade = CascadeType.ALL)
    private  List<Comment> commentList;

}
package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String email;

    private String userPhone;
    private LocalDate Birthdate;
    private String CIN;
    private String UnyName;
    private String Adresse;
    private int Score;
    private String CV;
    private String Gender;
    private String Grade;
    private int isverified;
    private String verificationToken;
    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    /*
    private boolean desactivate;
    @Temporal(TemporalType.DATE)
    private Date lastLoginDate;
    @Temporal(TemporalType.DATE)
    private Date dateCreate;
    @JsonIgnore
    private boolean isConnected;*/

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

    @OneToOne
    private image Photo;

    @JsonIgnore
    @OneToMany(mappedBy = "createdBy")
    private List<Opportunity> opportunities = new ArrayList<>();

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

    @OneToMany(mappedBy = "user")
    private List<Condidacy> candidacies = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
    public List<Participant> roomsList;
    @OneToMany(mappedBy = "rdv", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<Appointement> Appointements;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Rating> ratingList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> postList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> commentList;
    @JsonIgnore
    @OneToMany(mappedBy = "userQ")
    private List<Question> questions = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "userRec")
    private List<Reclamation> reclamations = new ArrayList<>();


}

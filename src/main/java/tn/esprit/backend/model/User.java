package tn.esprit.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
        @Id
        private String userName;
        private String userFirstName;
        private String userLastName;
        private String Email;
        private String Photo;
        private String UnyName;
        private Boolean Status;


//        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//        @JoinTable(name = "USER_ROLE",
//                joinColumns = {
//                        @JoinColumn(name = "USER_ID")
//                },
//                inverseJoinColumns = {
//                        @JoinColumn(name = "ROLE_ID")
//                }
//        )

//        public String getUserName() {
//            return userName;
//        }
//
//        public void setUserName(String userName) {
//            this.userName = userName;
//        }
//
//        public String getUserFirstName() {
//            return userFirstName;
//        }
//
//        public void setUserFirstName(String userFirstName) {
//            this.userFirstName = userFirstName;
//        }
//
//        public String getUserLastName() {
//            return userLastName;
//        }
//
//        public void setUserLastName(String userLastName) {
//            this.userLastName = userLastName;
//        }

    @JsonIgnore
            @OneToMany(mappedBy = "participant", cascade = CascadeType.ALL)
            public List<Participant> roomList;


}

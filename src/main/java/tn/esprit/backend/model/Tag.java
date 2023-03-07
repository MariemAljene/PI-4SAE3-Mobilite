package tn.esprit.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String tag;
    @JsonIgnore
    @ManyToMany(cascade= CascadeType.ALL,mappedBy="tag")
    private List<Message> msg;



}

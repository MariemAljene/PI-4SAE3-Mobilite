package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idPost;
    private String content;
    private Date publishDate;
    private String title;
    private String imageUrl;
    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<Comment> CommentList;

    @OneToOne
    private Rating rating;



}

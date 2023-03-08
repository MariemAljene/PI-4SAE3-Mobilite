package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Post;

import java.util.List;

public interface PostInterface {
    public Post addPost (Post p);
    public Post updatePost (Post p);
    public void deletePost (int idp);
    public List<Post> retrieveAllPost();
    public Post retrievePost(int idp);
}

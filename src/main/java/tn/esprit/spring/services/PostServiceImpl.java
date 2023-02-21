package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.interfaces.PostInterface;
import tn.esprit.spring.repositories.PostRepository;

import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements PostInterface {

@Autowired
    PostRepository postRepo;


    @Override
    public Post addPost(Post p) {
        return postRepo.save(p);
    }

    @Override
    public Post updatePost(Post p) {
        return postRepo.save(p);
    }

    @Override
    public void deletePost(int idp) {
        postRepo.deleteById(idp);
    }

    @Override
    public List<Post> retrieveAllPost() {
        List<Post> postList = postRepo.findAll();
        return postList;

    }

    @Override
    public Post retrievePost(int idp) {
        return postRepo.findById(idp).get();
    }
}

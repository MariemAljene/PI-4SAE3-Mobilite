package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.interfaces.CommentInterface;
import tn.esprit.spring.repositories.CommentRepository;

import java.util.List;

@Service
@Slf4j
public class CommentService implements CommentInterface {

    @Autowired
    CommentRepository commentRepo;


    @Override
    public Comment addComment(Comment c) {
        return commentRepo.save(c);
    }

    @Override
    public Comment updateComment(Comment c) {
        return commentRepo.save(c);
    }

    @Override
    public void deleteComment(int idc) {
        commentRepo.deleteById(idc);
    }

    @Override
    public List<Comment> retrieveAllComment() {
        List<Comment> comment=commentRepo.findAll();
        return comment;
    }

    @Override
    public Comment retrieveComment(int idc) {
        return commentRepo.findById(idc).get();
    }
}

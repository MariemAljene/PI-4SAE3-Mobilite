package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.Rating;

import java.util.List;

public interface RatingInterface {
    public Rating addRating (Rating r);
    public Rating updateRating (Rating r);
    public void deleteRating (int idr);
    public List<Rating> retrieveAllRating();
    public Post retrieveRating(int idr);
}

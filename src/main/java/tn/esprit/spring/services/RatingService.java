package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Post;
import tn.esprit.spring.entities.Rating;
import tn.esprit.spring.interfaces.RatingInterface;

import java.util.List;

@Service
@Slf4j
public class RatingService implements RatingInterface {

    @Override
    public Rating addRating(Rating r) {
        return null;
    }

    @Override
    public Rating updateRating(Rating r) {
        return null;
    }

    @Override
    public void deleteRating(int idr) {

    }

    @Override
    public List<Rating> retrieveAllRating() {
        return null;
    }

    @Override
    public Post retrieveRating(int idr) {
        return null;
    }
}

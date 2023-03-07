package tn.esprit.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.backend.model.Tag;

public interface TagRepository extends JpaRepository<Tag,String> {
}

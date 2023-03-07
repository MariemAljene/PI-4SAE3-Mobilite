package tn.esprit.backend.Services;

import tn.esprit.backend.model.Tag;

import java.util.List;

public interface TagService {

    public void addTag(Tag t);
    public void updateTag(Tag t);
    public List<Tag> allTags() ;
    public int countTags(Tag t);
    public void deleteTag(String tag) ;
}

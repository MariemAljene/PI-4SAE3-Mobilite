package tn.esprit.backend.Services;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.backend.Repository.MessageRepository;
import tn.esprit.backend.Repository.TagRepository;
import tn.esprit.backend.model.Message;
import tn.esprit.backend.model.Tag;

import java.util.List;

@Service
public class TagServiceImp implements  TagService{
    @Autowired
    TagRepository tr;
    @Autowired
    MessageRepository pr;
    @Override
    public void addTag(Tag t){
        //String eng=GoogleTTS_Translate.google_Translate("en",t.getTag());

        System.out.println("Tag="+t.getTag());
        tr.save(t);
    }

    @Override
    public void updateTag(Tag t) {
        if (tr.findById(t.getTag()).isPresent())
            tr.save(t);
        else
            System.out.println("doesnt exist");

    }

    @Override
    public List<Tag> allTags() {
        //System.out.println("Tag=");
        List<Tag> tags =  (List<Tag>) tr.findAll();
        System.out.println("tags : "+tags);
        return tags;
    }

    @Override
    public int countTags(Tag t){

        int count=0;
        System.out.println("TAG="+t.getTag());
        for(Message p:pr.findAll())
        {
            if(p.getTag().contains(t))
                count++;
        }
        System.out.println("COUNT"+count);
        return count;
    }

    @Override
    public void deleteTag(String tag) {
        // TODO Auto-generated method stub

    }
}

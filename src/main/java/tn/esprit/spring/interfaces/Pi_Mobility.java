package tn.esprit.spring.interfaces;

import tn.esprit.spring.entities.Condidacy;
import tn.esprit.spring.entities.Opportunity;
import tn.esprit.spring.entities.User;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

public interface Pi_Mobility {
    public List<Opportunity> findAllOpportunities() ;
    public Optional<Opportunity> findOpportunityById(Integer id) ;
    public Opportunity createOpportunity(Opportunity opportunity,String id_Partner) ;
    public Opportunity updateOpportunity(Opportunity opportunity);
    public void deleteOpportunityById(Integer id) ;
    public List<Condidacy> findAllCandidates();
    public Optional<Condidacy> findCandidateById(Integer id);
    public Condidacy createCandidateAndAssignEtudiant(Condidacy condidacy, String Id_Student ,int Id_Opportunity) ;
    public Condidacy updateCandidate(Condidacy condidacy) ;
    public void deleteCandidateById(Integer id);
 public  List<Opportunity> RetreiveOpportunitiesForStudentbySpecialite(String id_Student);

public List<Condidacy> RtreiveStudentCondidacies(String id_Student); //Reste
     Condidacy UpdateCondidacy(Integer id_Condidacy);
    List<Condidacy> trierEtudiantsParScore(Integer Id_Opportunity);
    List<Condidacy> CalculScore(Integer Id_Opportunity);
   void sendEmailToTopNCandidates(int n, int opportunityId) throws MessagingException ;
    public List<Condidacy> getTopNCandidatures(Integer opportunityId);
    public void sendSelectedCandidatesEmails(Integer opportunityId);

}

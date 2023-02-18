package tn.esprit.spring.services;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Condidacy;
import tn.esprit.spring.entities.Opportunity;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.status;
import tn.esprit.spring.interfaces.Pi_Mobility;
import tn.esprit.spring.repositories.CondidacyRepository;
import tn.esprit.spring.repositories.OpportunityRepository;
import tn.esprit.spring.repositories.RoleRepository;
import tn.esprit.spring.repositories.UserRepositoy;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class Pi_MobilityImplementation implements Pi_Mobility {
    @Autowired
    CondidacyRepository condidacyRepository;
    @Autowired
    OpportunityRepository opportunityRepository ;
    @Autowired
    UserRepositoy userRepository ;
    @Autowired
    RoleRepository roleRepository;
@Autowired
JavaMailSender javaMailSender;
    @Override
    public List<Opportunity> findAllOpportunities() {
        return opportunityRepository.findAll();
    }

    @Override
    public Optional<Opportunity> findOpportunityById(Integer id) {
        return opportunityRepository.findById( id);
    }

    @Override
    public Opportunity createOpportunity(Opportunity opportunity,String Id_Partner) {
 User Partner =userRepository.findById(Id_Partner).orElse(null);
 opportunity.setCreatedBy(Partner);

 return opportunityRepository.save(opportunity);


    }

    @Override
    public Opportunity updateOpportunity(Opportunity opportunity) {
       // opportunity.setId_Opportunity()
        return opportunityRepository.save(opportunity);
    }

    @Override
    public void deleteOpportunityById(Integer id) {
opportunityRepository.deleteById(id);
    }

    @Override
    public List<Condidacy> findAllCandidates() {
        return condidacyRepository.findAll();
    }

    @Override
    public Optional<Condidacy> findCandidateById(Integer id) {
        return condidacyRepository.findById(id);
    }

    @Override
    public Condidacy createCandidateAndAssignEtudiant(Condidacy condidacy, String Id_Student,int ID_Opportuinity) {
      condidacy.setStatus(status.In_Progress);
        User user=userRepository.findById(Id_Student).orElse(null);
Opportunity opportunity =opportunityRepository.findById(ID_Opportuinity).orElse(null);
        condidacy.setUser(user);
  condidacy.setOpportunity(opportunity);
condidacy.setScore(0);
        return condidacyRepository.save(condidacy);
    }

    @Override
    public Condidacy updateCandidate(Condidacy condidacy) {
        return condidacyRepository.save(condidacy);
    }

    @Override
    public void deleteCandidateById(Integer id) {
                   condidacyRepository.deleteById(id);
    }
    public String extraireChaine(String chaine) {
        int index_chiffre = -1;
        for (int i = 1; i < chaine.length(); i++) {
            if (Character.isDigit(chaine.charAt(i))) {
                index_chiffre = i;
                break;
            }
        }
        if (index_chiffre > 1) {
            return chaine.substring(1, index_chiffre);
        } else {
            return null;
        }
    }

    @Override
    public List<Opportunity> RetreiveOpportunitiesForStudentbySpecialite(String id_Student) {
       User Student=userRepository.findById(id_Student).orElse(null);

       String specialite = extraireChaine(Student.getGrade());
      // System.out.println(specialite);

           List<Opportunity> opportunities =new ArrayList<>();

        for(Opportunity op:opportunityRepository.findAll())
       {String sp= String.valueOf(op.getSpecialite());
           if(sp.toUpperCase().equals(specialite.toUpperCase()) || sp.toUpperCase().equals("ALL"))
           {
                  opportunities.add(op);
                  System.out.println(op.getSpecialite());
           }
       }


        return  opportunities;
        }

    @Override
    public List<Condidacy> RtreiveStudentCondidacies(String id_Student) {
        User student=userRepository.findById(id_Student).orElse(null);
        List<Condidacy> condidacies =student.getCandidacies();

        return condidacies;
    }

    @Override
    public Condidacy UpdateCondidacy(Integer id_Condidacy) {
        Condidacy condidacy= condidacyRepository.findById(id_Condidacy).orElse(null);
        condidacy.setStatus(status.Accepted);

        return condidacyRepository.save(condidacy);
    }


    public String UpdateCondidacy(Condidacy condidacy) {

        condidacy.setStatus(status.Accepted);
        return condidacy.getUser().getUserName();
    }
    public List<Condidacy> trierEtudiantsParScore(Integer Id_Opportunity) {

        List<Condidacy> ToSort =new ArrayList<>();
        Opportunity opportunity =opportunityRepository.findById(Id_Opportunity).orElse(null);
        for(Condidacy condidacy:condidacyRepository.findAll())
        {
            if (condidacy.getOpportunity().getId_Opportunity()==Id_Opportunity)
            { float coefTotal =opportunity.getCoef1stYear()+opportunity.getCoef2stYear()+opportunity.getCoef3stYear();
                condidacy.setScore((condidacy.getMoyenne_1year()*opportunity.getCoef1stYear()+condidacy.getMoyenne_2year()*opportunity.getCoef2stYear()+condidacy.getMoyenne_3year()*opportunity.getCoef3stYear())/coefTotal);
                condidacyRepository.save(condidacy);
                ToSort.add(condidacy);
            }
        }
        // Trier la liste des étudiants pour l'opportunité donnée par ordre décroissant de score
        ToSort.sort(Comparator.comparing(Condidacy::getScore).reversed());
        return ToSort;
    }

    @Override
    public List<Condidacy> CalculScore(Integer Id_Opportunity) {
        Opportunity opportunity =opportunityRepository.findById(Id_Opportunity).orElse(null);
        for(Condidacy condidacy:condidacyRepository.findAll())
        {
            if (condidacy.getOpportunity().getId_Opportunity()==Id_Opportunity)
            {
                condidacy.setScore((condidacy.getMoyenne_1year()+condidacy.getMoyenne_2year()*2+condidacy.getMoyenne_3year()*3)/6);
                condidacyRepository.save(condidacy);
            }
        }
        return condidacyRepository.findAll();
    }
    public void sendEmailToTopNCandidates(int n, int opportunityId) throws MessagingException {
        List<Condidacy> sortedCondidacyList =getTopNCandidatures(opportunityId);

        for (int i = 0; i < Math.min(n, sortedCondidacyList.size()); i++) {
            Condidacy condidacy = sortedCondidacyList.get(i);

            // Envoyer un e-mail à l'étudiant avec le score et le résultat de sa candidature
            String to = condidacy.getUser().getEmail();
            String subject = "Résultat de votre candidature à l'opportunité " + opportunityId;
            String message = "Bonjour " + condidacy.getUser().getUserFirstName() + ",\n\n"
                    + "Nous sommes heureux de vous informer que votre candidature pour l'opportunité " + opportunityId
                    + " a été acceptée avec un score de " + condidacy.getScore() + ".\n\n"
                    + "Félicitations !\n\n"
                    + "Cordialement,\n"
                    + "L'équipe de recrutement";

            // Envoyer l'e-mail en utilisant JavaMail
            MimeMessage email =  javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message, true);
            javaMailSender.send(email);
        }
       /* SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("mariemaljene0@gmail.com");
        message.setSubject("test");
       */
    }
    public List<Condidacy> getTopNCandidatures(Integer opportunityId) {
        Opportunity opportunity = opportunityRepository.findById(opportunityId).orElse(null);
        int n = opportunity.getCapacity();
        // Trier les candidatures par score en utilisant la méthode que vous avez implémentée
         List<Condidacy> CondidacyTOP =trierEtudiantsParScore( opportunityId);
        // Récupérer les n candidatures avec les scores les plus élevés
        List<Condidacy> topNCandidatures = new ArrayList<>();
        if(opportunity.getNeeds()==0)
        {
            for (int i = 0; i < n && i < CondidacyTOP.size(); i++)
            {
                topNCandidatures.add(CondidacyTOP.get(i));
                CondidacyTOP.get(i).setStatus(status.Accepted);
                userRepository.save(CondidacyTOP.get(i).getUser());


            }
        }
        else
        {
            int count = 0;
            for (Condidacy candidacy : CondidacyTOP) {
                if (candidacy.getScore() >= opportunity.getNeeds()) {
                    topNCandidatures.add(candidacy);
                    count++;
                    if (count == n) {
                        break;
                    }
                    candidacy.setStatus(status.Accepted);
                    userRepository.save(candidacy.getUser());

                }
                else   candidacy.setStatus(status.Refused);
                       userRepository.save(candidacy.getUser());
            }
        }


        return topNCandidatures;
    }
    public void sendSelectedCandidatesEmails(Integer opportunityId) {
        List<Condidacy> selectedCandidates = getTopNCandidatures(opportunityId);

        for (Condidacy candidate : selectedCandidates) {
            // Créer un objet `SimpleMailMessage` pour l'email à envoyer
            SimpleMailMessage message = new SimpleMailMessage();

            // Définir le destinataire et le sujet de l'email
            message.setTo(candidate.getUser().getEmail());
            message.setSubject("Opportunity selection");

            // Définir le corps du message de l'e-mail
            message.setText("Dear " + candidate.getUser().getUserName() + ",\n\n" +
                    "Congratulations! You have been selected for the following opportunity:\n\n" +
                    opportunityRepository.findById(opportunityId).get().getTitle() + "\n\n" +
                    "Please contact the opportunity provider for further details.\n\n" +
                    "Best regards,\n" +
                    "Your Application Team");

            // Envoyer l'email
            javaMailSender.send(message);
        }

}}

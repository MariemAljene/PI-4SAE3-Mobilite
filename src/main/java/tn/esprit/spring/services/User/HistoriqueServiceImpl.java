package tn.esprit.spring.services.User;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Appointement;
import tn.esprit.spring.entities.Historique;
import tn.esprit.spring.interfaces.IHistoriqueService;
import tn.esprit.spring.repositories.AppointementRepository;
import tn.esprit.spring.repositories.IHistoriqueRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HistoriqueServiceImpl implements IHistoriqueService {
    @Autowired
    IHistoriqueRepository historiquerepository;
    @Autowired
    AppointementRepository appointementrepository;

    @Override
    public List<Historique> retrieveAllHist() {
        return null;
    }



    @Override
    public Historique addHistorique(Integer idAppointement) {
        Appointement appointment = appointementrepository.findById(idAppointement).orElse(null);
        Historique hq = new Historique();
        hq.setEmail(appointment.getEmail());
        hq.setPhoneNumber(appointment.getPhoneNumber());
        hq.setDateRdv(appointment.getDateRdv());

        return historiquerepository.save(hq);
    }
    @Override
    public Historique updateHistorique(Integer idAppointement, Integer DurationAppointment, String NamePartner) {
        Historique historique = historiquerepository.findById(idAppointement).orElse(null);

        if (historique != null) {
            historique.setDurationAppointment(DurationAppointment);
            historique.setNamePartner(NamePartner);
            historique = historiquerepository.save(historique);
        }

        return historique;
    }

    public String generateAppointmentReport(int idAppointement , String NamePartner) {
        List<Historique> historiques = historiquerepository.findAll();


        if (NamePartner != null && !NamePartner.isEmpty()) {
            historiques = historiques.stream()
                    .filter(a -> a.getNamePartner().equalsIgnoreCase(NamePartner))
                    .collect(Collectors.toList());
        }

        // Build the report string
        StringBuilder reportBuilder = new StringBuilder();
        for (Historique appointment : historiques) {
            reportBuilder.append("Appointment ID: ").append(appointment.getIdAppointement()).append("\n");
            reportBuilder.append("Email: ").append(appointment.getEmail()).append("\n");
            reportBuilder.append("Phone Number: ").append(appointment.getPhoneNumber()).append("\n");
            reportBuilder.append("Date: ").append(appointment.getDateRdv()).append("\n");
            reportBuilder.append("Duration: ").append(appointment.getDurationAppointment()).append("\n");
            reportBuilder.append("Partner Name: ").append(appointment.getNamePartner()).append("\n");
            reportBuilder.append("Report: ").append(appointment.getReport()).append("\n");
            reportBuilder.append("\n");
        }

        return reportBuilder.toString();
    }
    public double getAverageDuration() {
        List<Historique> historiques = historiquerepository.findAll();
        double sumDuration = historiques.stream().mapToInt(Historique::getDurationAppointment).sum();
        return sumDuration / historiques.size();
    }

    @Override
    public List<Historique> getHistoriques() {
        return null;
    }
}


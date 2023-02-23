package com.example.appoitement.interfaces;

import com.example.appoitement.entities.Historique;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IHistoriqueService {

    public List<Historique> retrieveAllHist();

    public Historique addHistorique(Integer idAppointement) ;

    Historique updateHistorique(Integer idAppointement, Integer DurationAppointment, String NamePartner);
    public String generateAppointmentReport(int idAppointement , String NamePartner);
}

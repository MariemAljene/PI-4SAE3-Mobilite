package com.example.appoitement.interfaces;

import com.example.appoitement.entities.Appointement;
import com.example.appoitement.repositories.AppointementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public interface IAppointementService {




    public List<Appointement> retrieveAllRdv();
    public Appointement updateRdv(Appointement ap) ;
    public Appointement addRdv(Appointement ap) ;

    public Appointement retrieveRdv(Integer idAppointement) ;

    public void removeRdv(Integer idAppointement);
    public Appointement updateById(Integer idAppointement, Appointement appointement);
    public List<Appointement> findByDateRangeSortedByDateDemande(LocalDate startDate, LocalDate endDate);
    public boolean isAppointmentAvailable(LocalDate date);

}

package com.example.appoitement.services;

import com.example.appoitement.entities.Appointement;
import com.example.appoitement.interfaces.IAppointementService;
import com.example.appoitement.repositories.AppointementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AppointementServiceImpl implements IAppointementService {
    @Autowired
    AppointementRepository appointementrepository;
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public List<Appointement> retrieveAllRdv() {
        return null;
    }
    public Appointement updateRdv(Appointement ap) {

        return appointementrepository.save(ap);
    }

    public Appointement addRdv(Appointement ap) {

        Appointement savedAppointment =  appointementrepository.save(ap);
       // sendNotificationEmail(savedAppointment);
        return savedAppointment;

    }

    public Appointement retrieveRdv(Integer idAppointement) {

        return appointementrepository.findById(idAppointement).orElse(null);
    }

    public void removeRdv(Integer idAppointement) {
        appointementrepository.deleteById(idAppointement);
    }

    public Appointement updateById(Integer idAppointement, Appointement appointement) {
        Optional<Appointement> optionalAppointement = appointementrepository.findById(idAppointement);

        if (optionalAppointement.isPresent()) {
            Appointement existingAppointement = optionalAppointement.get();
            existingAppointement.setFirstname(appointement.getFirstname());
            existingAppointement.setLastname(appointement.getLastname());
            existingAppointement.setEmail(appointement.getEmail());
            existingAppointement.setPhoneNumber(appointement.getPhoneNumber());
            existingAppointement.setDateDemande(appointement.getDateDemande());
            existingAppointement.setDateRdv(appointement.getDateRdv());
            existingAppointement.setStatus(appointement.getStatus());
            return appointementrepository.save(existingAppointement);
        } return null ;

}

    public List<Appointement> findByDateRangeSortedByDateDemande(LocalDate startDate, LocalDate endDate) {
        return appointementrepository.findByDateDemandeBetweenOrderByDateDemande(startDate, endDate);
    }
    public boolean isAppointmentAvailable(LocalDate date) {
        int count = appointementrepository.countAppointmentsOnDate(date);
        return count == 0;
    }



}

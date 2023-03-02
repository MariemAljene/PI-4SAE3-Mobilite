package com.example.appoitement.services;

import com.example.appoitement.entities.Appointement;
import com.example.appoitement.entities.WaitingList;
import com.example.appoitement.interfaces.IAppointementService;
import com.example.appoitement.repositories.AppointementRepository;
import com.example.appoitement.repositories.WaitingListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AppointementServiceImpl implements IAppointementService {
    @Autowired
    AppointementRepository appointementrepository;
    @Autowired
    WaitingListRepository waitingListRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public List<Appointement> retrieveAllRdv() {
        return null;
    }
    public Appointement updateRdv(Appointement ap) {

        return appointementrepository.save(ap);
    }

    /*public Appointement addRdv(Appointement ap) {

        Appointement savedAppointment =  appointementrepository.save(ap);
       // sendNotificationEmail(savedAppointment);
        return savedAppointment;

    }*/
    public Appointement addRdv(Appointement ap) {

        LocalDate date = ap.getDateRdv();
        if (!isAppointmentAvailable(date)) {
            WaitingList waitingList = new WaitingList();
            waitingList.setEmail(ap.getEmail());
            waitingList.setPhoneNumber(ap.getPhoneNumber());
            waitingList.setPaiment(0f);
            waitingList.setAppointement(ap);
            waitingListRepository.save(waitingList);
            throw new RuntimeException("Appointment is not available on " + date + ", added to waiting list.");
        }
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

   /* public void scheduleAppointmentFromWaitingList(LocalDate dateDemande) {
        List<WaitingList> waitingList = waitingListRepository.findByDateRequested(dateDemande);
        for (WaitingList waitingAppointment : waitingList) {
            if (isAppointmentAvailable(waitingAppointment.getAppointement().getDateDemande())) {
                Appointement appointment = waitingAppointment.getAppointement();
                appointementrepository.save(appointment); // Save the appointment to the appointment table
                waitingListRepository.delete(waitingAppointment); // Remove the appointment from the waiting list
            }
        }
    }*/

/*    public void cancelAppointment(Integer idAppointement) {
        Optional<Appointement> appointmentOptional = appointementrepository.findById(idAppointement);
        if (appointmentOptional.isPresent()) {
            Appointement appointement = appointmentOptional.get();
            appointement.setStatus(false);
            appointementrepository.save(appointement);
        }
    }*/
    public List<Appointement> findExpiredAppointments(LocalDate dateRdv) {
        return appointementrepository.findByStatusAndDateRdvBefore(true, dateRdv);
    }

    }


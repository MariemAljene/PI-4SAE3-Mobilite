package com.example.appoitement.configuration;

import com.example.appoitement.entities.Appointement;
import com.example.appoitement.services.AppointementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AppointmentCanceler {
    @Autowired
    private AppointementServiceImpl appointementService;

    @Scheduled(fixedRate = 2000) //exécuter toutes les 24 heures
    public void cancelExpiredAppointments() {
        // Calculer la date limite : maintenant moins un mois
        LocalDate dateRdv = LocalDate.now().minusMonths(1);

        // Trouver tous les rendez-vous actifs qui ont dépassé la date limite
        List<Appointement> expiredAppointments = appointementService.findExpiredAppointments(dateRdv);

        // Annuler tous les rendez-vous qui ont dépassé la date limite
        for (Appointement appointment : expiredAppointments) {
            appointementService.removeRdv(appointment.getIdAppointement());
        }
    }
}

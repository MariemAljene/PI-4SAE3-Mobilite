package com.example.appoitement.controllers;

import com.example.appoitement.entities.Appointement;
import com.example.appoitement.entities.Historique;
import com.example.appoitement.interfaces.IAppointementService;
import com.example.appoitement.interfaces.IHistoriqueService;
import com.example.appoitement.repositories.AppointementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@Slf4j
//@AllArgsConstructor
@RequestMapping("/rendezvous")
public class AppointementRestController {
    @Autowired
    // @Qualifier("AppointementServiceImpl")
    IHistoriqueService historiqueService;


    @Autowired
    // @Qualifier("AppointementServiceImpl")
    IAppointementService appointementService;

    @GetMapping("/retrieve-all-rendezvous")
    public List<Appointement> getAppointement() {
        List<Appointement> appointementList = appointementService.retrieveAllRdv();
        return appointementList;

    }

    @GetMapping("/retrieve-rendezvous/{rendezvous-id}")
    public Appointement retrieveAppointement(@PathVariable("rendezvous-id") Integer idAppointement) {
        return appointementService.retrieveRdv(idAppointement);
    }


    @PostMapping("/add-rendezvous")
    public Appointement addRdv(@RequestBody Appointement a) {
        Appointement savedAppointment = appointementService.addRdv(a);

        return savedAppointment;
    }

    @DeleteMapping("/remove-rendezvous/{appointement-id}")
    public void removeRdv(@PathVariable("appointement-id") Integer idAppointement) {

        appointementService.removeRdv(idAppointement);
    }

    @PutMapping("/update-rdv")
    public Appointement updateRdv(@RequestBody Appointement a) {
        Appointement appointement = appointementService.updateRdv(a);
        return appointement;
    }

    @PutMapping("/{id}")
    public Appointement updateAppointementById(@PathVariable("id") Integer idAppointement,
                                               @RequestBody Appointement appointement) {
        Appointement updatedAppointement = appointementService.updateById(idAppointement, appointement);

        return appointement;
    }

    @GetMapping("/range-all-rendezvous")
    public List<Appointement> getMyAppointementByDateRangeSortedByDateDemande(
            @RequestParam("start-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return appointementService.findByDateRangeSortedByDateDemande(startDate, endDate);
    }
    @GetMapping("/checkAvailability")
    public ResponseEntity<?> checkAvailability(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        boolean isAvailable = appointementService.isAppointmentAvailable(date);

        if (isAvailable) {
            return ResponseEntity.ok("Appointment is available on " + date);
        } else {
            return ResponseEntity.badRequest().body("Appointment is not available on " + date);
        }
    }

    @PostMapping("/appointments/{id}/convert")
    public ResponseEntity<Historique> convertAppointmentToHistorique(@PathVariable("id") Integer id) {
        Historique historique = historiqueService.addHistorique(id);
        if (historique == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(historique);
        }

        }
    @PutMapping("/historique/{idAppointement}")
    public ResponseEntity<Historique> updateHistorique(@PathVariable Integer idAppointement,
                                                       @RequestParam Integer durationAppointment,
                                                       @RequestParam String namePartner) {

        Historique updatedHistorique = historiqueService.updateHistorique(idAppointement, durationAppointment, namePartner);

        if (updatedHistorique == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedHistorique);
    }

    @GetMapping("/report")
    public String generateAppointmentReport(@RequestParam(required = false) Integer id, @RequestParam(required = false) String NamePartner) {
        String report = historiqueService.generateAppointmentReport(id, NamePartner);
        return report;
    }
}






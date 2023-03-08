package com.example.appoitement.services;

import com.example.appoitement.entities.Appointement;
import com.example.appoitement.entities.Historique;
import com.example.appoitement.repositories.IHistoriqueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PostPersist;

public class AppointementListener {
   /* @Autowired
    private IHistoriqueRepository historiqueRepository;

    @PostPersist
    public void onPostPersist(Appointement appointement) {
        Historique historique = new Historique();
        historique.setEmail(appointement.getEmail());
        historique.setPhoneNumber(appointement.getPhoneNumber());
        historique.setDateRdv(appointement.getDateRdv());
        // set other fields as needed
        historiqueRepository.save(historique);
    }*/
}

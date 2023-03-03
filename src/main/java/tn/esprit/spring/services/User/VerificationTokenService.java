package tn.esprit.spring.services.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.VerificationToken;
import tn.esprit.spring.repositories.VerificationTokenRepository;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class VerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public VerificationToken createVerificationToken(User user) {
        String token = generateVerificationToken();
        LocalDateTime expiryDate = LocalDateTime.now().plusDays(1);

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(expiryDate);
        verificationTokenRepository.save(verificationToken);

        return verificationToken;
    }

    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    public void saveVerificationToken(VerificationToken verificationToken) {
        verificationTokenRepository.save(verificationToken);
    }

    // méthode pour valider si le jeton est encore valide
    public boolean isValidVerificationToken(VerificationToken verificationToken) {
        return verificationToken != null && !isExpired();
    }
    public boolean isExpired() {
        VerificationToken verificationToken = new VerificationToken();
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(verificationToken.getExpiryDate());
    }
    // méthode pour générer un jeton aléatoire
    public String generateVerificationToken() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 30;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}

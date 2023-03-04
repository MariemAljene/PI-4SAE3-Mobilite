package tn.esprit.spring.services.Email;

public interface EmailService{

    public void sendMail(String to, String subject, String text);

}

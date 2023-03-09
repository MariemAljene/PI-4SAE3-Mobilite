package tn.esprit.spring.Exceptions;

public class InvalidSpecialityException extends RuntimeException {
    public InvalidSpecialityException(String message) {
        super(message);
    }
}

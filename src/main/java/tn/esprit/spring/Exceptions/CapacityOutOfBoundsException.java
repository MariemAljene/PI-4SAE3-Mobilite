package tn.esprit.spring.Exceptions;

public class CapacityOutOfBoundsException extends RuntimeException {
    public CapacityOutOfBoundsException(String message) {
        super(message);
    }
}
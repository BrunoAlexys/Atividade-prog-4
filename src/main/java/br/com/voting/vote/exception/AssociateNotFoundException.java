package br.com.voting.vote.exception;

public class AssociateNotFoundException extends RuntimeException {
    public AssociateNotFoundException(String message) {
        super(message);
    }

    public AssociateNotFoundException(String message,Throwable cause) {
        super(message,cause);
    }
}

package br.com.voting.vote.exception;

public class AssociateHasAlreadyVotedException extends RuntimeException {
    public AssociateHasAlreadyVotedException(String message) {
        super(message);
    }

    public AssociateHasAlreadyVotedException(String message,Throwable cause) {
        super(message,cause);
    }
}

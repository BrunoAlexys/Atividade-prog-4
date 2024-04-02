package br.com.voting.vote.exception;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(String message) {
        super(message);
    }

    public TopicNotFoundException(String message,Throwable cause) {
        super(message,cause);
    }
}

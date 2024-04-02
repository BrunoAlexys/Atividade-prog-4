package br.com.voting.vote.exception;

import java.time.LocalDateTime;

public class ExceptionDetails {
    protected String title;
    protected int status;
    protected String details;
    protected String developerMessage;
    protected LocalDateTime timestamp;

    public ExceptionDetails(){

    }

    public ExceptionDetails(String title, int status, String details, String developerMessage, LocalDateTime timestamp) {
        this.title = title;
        this.status = status;
        this.details = details;
        this.developerMessage = developerMessage;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ExceptionDetails title(String title) {
        this.title = title;
        return this;
    }

    public ExceptionDetails status(int status) {
        this.status = status;
        return this;
    }

    public ExceptionDetails details(String details) {
        this.details = details;
        return this;
    }

    public ExceptionDetails developerMessage(String developerMessage) {
        this.developerMessage = developerMessage;
        return this;
    }

    public ExceptionDetails timestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public ExceptionDetails build() {
        return new ExceptionDetails(title, status, details, developerMessage, timestamp);
    }
}

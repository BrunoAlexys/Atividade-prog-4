package br.com.voting.vote.handler;

import br.com.voting.vote.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AssociateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDetails handlerNotFoundException(AssociateNotFoundException exception) {
        return new ExceptionDetails()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title(HttpStatus.NOT_FOUND.name())
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
    }

    @ExceptionHandler(TopicNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDetails handlerNotFoundException(TopicNotFoundException exception) {
        return new ExceptionDetails()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title(HttpStatus.NOT_FOUND.name())
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
    }

    @ExceptionHandler(SessionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDetails handlerNotFoundException(SessionNotFoundException exception) {
        return new ExceptionDetails()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title(HttpStatus.NOT_FOUND.name())
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
    }

    @ExceptionHandler(AssociateHasAlreadyVotedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDetails handlerNotFoundException(AssociateHasAlreadyVotedException exception) {
        return new ExceptionDetails()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .title(HttpStatus.UNAUTHORIZED.name())
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
    }

    @ExceptionHandler(SessionExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionDetails handlerNotFoundException(SessionExpiredException exception) {
        return new ExceptionDetails()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNAUTHORIZED.value())
                .title(HttpStatus.UNAUTHORIZED.name())
                .details(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
    }
}

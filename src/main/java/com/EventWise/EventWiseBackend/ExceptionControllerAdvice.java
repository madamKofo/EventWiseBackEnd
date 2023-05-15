package com.EventWise.EventWiseBackend;

import com.EventWise.EventWiseBackend.exceptions.EventNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.internalServerError().body("An internal Error happened, " +
                "please try again later or contact support");
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFoundException(Exception ex) {
        return ResponseEntity.notFound().build();
    }

}
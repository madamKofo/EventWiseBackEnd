package com.EventWise.EventWiseBackend.exceptions;

public class EventNotFoundException extends RuntimeException  {
    public EventNotFoundException(String message) {
        super(message);
    }
}

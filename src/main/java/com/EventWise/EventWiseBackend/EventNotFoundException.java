package com.EventWise.EventWiseBackend;

public class EventNotFoundException extends RuntimeException  {
    public EventNotFoundException(String message) {
        super(message);
    }
}

package com.EventWise.EventWiseBackend.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String s) {
    }

    public UserNotFoundException(String userName, String password) {
        super(String.format("User not found for username=%s and the given password!", userName));
    }
}

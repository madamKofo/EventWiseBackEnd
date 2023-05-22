package com.EventWise.EventWiseBackend.service;


import com.EventWise.EventWiseBackend.entities.Login;

public interface LoginService {

    boolean isLoginValid(Login login);
}

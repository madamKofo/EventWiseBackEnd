/*

package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.entities.Login;
import com.EventWise.EventWiseBackend.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserService userService;

    @Override
    public boolean isLoginValid(Login login) {
        try {
            userService.findUserByUserNameAndPassword(login.getUserName(), login.getPassword());
            return true;
        } catch (UserNotFoundException userNotFoundException) {
            return false;
        }
    }
}

*/

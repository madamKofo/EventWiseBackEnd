package com.EventWise.EventWiseBackend.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String displayName;
}

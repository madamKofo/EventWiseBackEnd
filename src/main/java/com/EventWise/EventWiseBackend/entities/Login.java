package com.EventWise.EventWiseBackend.entities;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    private String userName;
    private String password;
}

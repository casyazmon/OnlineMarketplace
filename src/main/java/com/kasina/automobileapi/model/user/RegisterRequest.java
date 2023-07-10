package com.kasina.automobileapi.model.user;

import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Set<String> roles;
}

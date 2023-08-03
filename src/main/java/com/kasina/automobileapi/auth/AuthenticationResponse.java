package com.kasina.automobileapi.auth;

import com.kasina.automobileapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private User user;
    /*private String email;
    private Long id;
    private String firstName;
    private String lastName;*/

}
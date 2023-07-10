package com.kasina.automobileapi.controller;

import com.kasina.automobileapi.auth.AuthenticationService;
import com.kasina.automobileapi.model.user.User;
import com.kasina.automobileapi.model.user.RegisterRequest;
import com.kasina.automobileapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

//    @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest registerRequest) {
//        User registeredUser = userService.registerUser(registerRequest);
//        return ResponseEntity.ok(registeredUser);
//    }


    // LOGIN FUNCTION
//    @PostMapping("/login")
//    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
//        Authentication authentication = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String token = jwtTokenUtil.generateToken(userDetails);
//        JwtResponse response = new JwtResponse(token);
//        return ResponseEntity.ok(response);
//    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}

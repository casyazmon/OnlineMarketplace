package com.kasina.automobileapi.auth;

import com.kasina.automobileapi.model.user.RegisterRequest;
import com.kasina.automobileapi.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.registerUser(request));
    }

//    @PostMapping("/register")
//    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest registerRequest) {
//        User registeredUser = authenticationService.registerUser(registerRequest);
//        return ResponseEntity.ok(registeredUser);
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @GetMapping("hello")
    public ResponseEntity<String> sayHello(){
        return  ResponseEntity.ok("Hello from secure endpoint");
    }
}

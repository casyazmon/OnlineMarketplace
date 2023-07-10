package com.kasina.automobileapi.service;

import com.kasina.automobileapi.model.Role;
import com.kasina.automobileapi.model.user.User;
import com.kasina.automobileapi.model.user.RegisterRequest;
import com.kasina.automobileapi.repository.RoleRepository;
import com.kasina.automobileapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    /*public User registerUser(RegisterRequest registerRequest) {
        Set<Role> roles = new HashSet<>();
        for (String roleName : registerRequest.getRoles()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleName));
            roles.add(role);
        }
        var user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .roles(roles)
                .build();
        return userRepository.save(user);
    }*/

//    public Authentication authenticateUser(String username, String password) {
//        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>()
        );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }
}

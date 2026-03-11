package com.e_feesh.service;

import com.e_feesh.dto.AuthRequestDTO;
import com.e_feesh.dto.AuthResponseDTO;
import com.e_feesh.exception.UsernameAlreadyExistsException;
import com.e_feesh.model.User;
import com.e_feesh.repository.UserRepository;
import com.e_feesh.security.JwtService;
import org.springframework.lang.Contract;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponseDTO register(AuthRequestDTO req) {
        if (userRepository.findByUsername(req.getUsername()).isPresent()) {
        throw new UsernameAlreadyExistsException("This username already exists.");
        }
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole("ROLE_USER");

        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser);
        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO login(AuthRequestDTO req) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        User user = userRepository.findByUsername(req.getUsername()).orElseThrow(() -> new UsernameAlreadyExistsException("This username already exists."));
        String token = jwtService.generateToken(user);
        return new AuthResponseDTO(token);
    }
}

package com.example.java_project.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_project.jwt.JwtHelper;
import com.example.java_project.model.JwtRequest;
import com.example.java_project.model.JwtResponse;
import com.example.java_project.repo.UserRepo;
import com.example.java_project.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;


@CrossOrigin(
	    origins = "*",
	    methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.POST}
	)
@RestController
@AllArgsConstructor
@RequestMapping("/welcome")

public class JwtAuthController {

	@Autowired
    private UserDetailsService userDetailsService;

    @Autowired
	private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepo userRepo;
    
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthController.class);

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody JwtRequest request, HttpServletRequest httpRequest) {
    	


        // Authenticate
        this.doAuthenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);
        logger.info("Generated Token: {}", token);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername())
                .build();

//        logger.info("Response: {}", response.toString());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);
            logger.info("Authentication successful for user: {}", username);
        } catch (BadCredentialsException e) {
            logger.error("Invalid credentials for user: {}", username);
            throw new BadCredentialsException("Credentials Invalid !!", e);
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
    
}

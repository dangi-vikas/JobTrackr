package com.auth.user.controller;

import com.auth.user.model.AuthenticationRequest;
import com.auth.user.model.AuthenticationResponse;
import com.auth.user.model.User;
import com.auth.user.service.CustomUserDetailsService;
import com.auth.user.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Authentication and Register API", description = "APIs for registering a user and authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    @Operation(summary = "User Authentication", description = "Returns the token after authenticating the user")
    @ApiResponse(responseCode = "200", description = "Successfully authenticated the user!")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    @Operation(summary = "User Registration", description = "Registers a User and save it to databases with a encrypted password")
    @ApiResponse(responseCode = "200", description = "Successfully created the user!")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    @GetMapping("/{username}")
    @Operation(summary = "User Details", description = "Provides the details for a given username")
    @ApiResponse(responseCode = "200", description = "Successfully fetched user details!")
    public ResponseEntity<?> getUserDetails(@PathVariable String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setEmail("test@example.com");

        return ResponseEntity.ok(user);
    }
}
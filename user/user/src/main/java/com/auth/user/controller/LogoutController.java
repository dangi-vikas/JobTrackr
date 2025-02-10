package com.auth.user.controller;

import com.auth.user.util.TokenBlacklist;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Logout API", description = "API for logging out the user")
public class LogoutController {

    @Autowired
    private TokenBlacklist tokenBlacklist;

    @PostMapping("/api/v1/users/logout")
    @Operation(summary = "User Logout", description = "Logs out a User deleting the token")
    @ApiResponse(responseCode = "200", description = "Successfully logged out!")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7); // Remove "Bearer " prefix
        tokenBlacklist.addToBlacklist(jwt); // Add token to blacklist
        return ResponseEntity.ok("Logged out successfully");
    }
}
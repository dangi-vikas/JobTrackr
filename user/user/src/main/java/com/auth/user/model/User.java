package com.auth.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
@Schema(description = "Details about a user")
public class User {
    @Id
    @Schema(description = "The unique ID of the user")
    private String id;

    @Schema(description = "The username of the user")
    private String username;

    @Schema(description = "The password for the user")
    private String password;

    @Schema(description = "The email of the user")
    private String email;

    @Schema(description = "The role of user i.e. Admin/Consumer")
    private String role;
}

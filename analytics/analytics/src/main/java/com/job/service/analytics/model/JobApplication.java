package com.job.service.analytics.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "applications")
@Schema(description = "Details about a job application")
public class JobApplication {
    @Id
    @Schema(description = "The unique ID of the job application")
    private String id;

    @Schema(description = "The ID of the user who created the application")
    private String userId;

    @Schema(description = "The name of the company")
    private String companyName;

    @Schema(description = "The title of the job")
    private String jobTitle;

    @Schema(description = "The status of the application (e.g., Applied, Interviewed, Rejected)")
    private String status;

    @Schema(description = "The date the application was submitted")
    private LocalDate applicationDate;

    @Schema(description = "The date the company responded")
    private LocalDate responseDate;

    @Schema(description = "Additional notes about the application")
    private String notes;
}

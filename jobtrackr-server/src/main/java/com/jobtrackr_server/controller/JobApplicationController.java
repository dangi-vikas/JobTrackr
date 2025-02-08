package com.jobtrackr_server.controller;

import com.jobtrackr_server.model.JobApplication;
import com.jobtrackr_server.service.JobApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
@Tag(name = "Job Application API", description = "APIs for managing job applications")
public class JobApplicationController {
    @Autowired
    JobApplicationService jobApplicationService;

    @GetMapping
    @Operation(summary = "Get all job applications", description = "Returns a list of all job applications.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of job applications")
    public List<JobApplication> getAllApplications() {
        return jobApplicationService.getAllApplications();
    }

    @PostMapping
    @Operation(summary = "Add a new job application", description = "Creates a new job application.")
    @ApiResponse(responseCode = "200", description = "Successfully added the job application")
    public JobApplication addApplication(@RequestBody JobApplication application) {
        return jobApplicationService.addApplication(application);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get job applications by user ID", description = "Returns a list of job applications for a specific user.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of job applications")
    public List<JobApplication> getApplicationsByUserId(@PathVariable String userId) {
        return jobApplicationService.getApplicationsByUserId(userId);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get job applications by status", description = "Returns a list of job applications with a specific status.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of job applications")
    public List<JobApplication> getApplicationsByStatus(@PathVariable String status) {
        return jobApplicationService.getApplicationsByStatus(status);
    }
}

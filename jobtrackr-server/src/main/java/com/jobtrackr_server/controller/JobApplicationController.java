package com.jobtrackr_server.controller;

import com.jobtrackr_server.model.JobApplication;
import com.jobtrackr_server.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
public class JobApplicationController {
    @Autowired
    JobApplicationService jobApplicationService;

    @GetMapping
    public List<JobApplication> getAllApplications() {
        return jobApplicationService.getAllApplications();
    }

    @PostMapping
    public JobApplication addApplication(@RequestBody JobApplication application) {
        return jobApplicationService.addApplication(application);
    }

    @GetMapping("/user/{userId}")
    public List<JobApplication> getApplicationsByUserId(@PathVariable String userId) {
        return jobApplicationService.getApplicationsByUserId(userId);
    }

    @GetMapping("/status/{status}")
    public List<JobApplication> getApplicationsByStatus(@PathVariable String status) {
        return jobApplicationService.getApplicationsByStatus(status);
    }
}

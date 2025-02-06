package com.jobtrackr_server.service;

import com.jobtrackr_server.model.JobApplication;
import com.jobtrackr_server.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JobApplicationService {
    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public List<JobApplication> getAllApplications() {
        return jobApplicationRepository.findAll();
    }

    public JobApplication addApplication(JobApplication application) {
        return jobApplicationRepository.save(application);
    }

    public List<JobApplication> getApplicationsByUserId(String userId) {
        return jobApplicationRepository.findByUserId(userId);
    }

    public List<JobApplication> getApplicationsByStatus(String status) {
        return jobApplicationRepository.findByStatus(status);
    }
}

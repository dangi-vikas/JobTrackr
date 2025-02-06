package com.jobtrackr_server.repository;

import com.jobtrackr_server.model.JobApplication;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobApplicationRepository extends MongoRepository<JobApplication, String> {
    List<JobApplication> findByUserId(String userId);
    List<JobApplication> findByStatus(String status);
}

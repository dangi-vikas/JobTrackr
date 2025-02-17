package com.job.service.analytics.repository;

import com.job.service.analytics.model.JobApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationRepository extends MongoRepository<JobApplication, String> {

    /**
     * Counts the number of job applications with a specific status.
     *
     * @param status The status to filter by (e.g., "Applied", "Interviewed").
     * @return The count of job applications with the specified status.
     */
    long countByStatusIgnoreCase(String status);
}
package com.job.service.analytics.service;


import com.job.service.analytics.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AnalyticsService {

    @Autowired
    private JobApplicationRepository repository;

    public Map<String, Long> getApplicationStatusCount() {
        Map<String, Long> statusCount = new HashMap<>();
        statusCount.put("Applied", repository.countByStatusIgnoreCase("Applied"));
        statusCount.put("Interviewed", repository.countByStatusIgnoreCase("Interviewed"));
        statusCount.put("Rejected", repository.countByStatusIgnoreCase("Rejected"));
        statusCount.put("Offered", repository.countByStatusIgnoreCase("Offered"));
        return statusCount;
    }
}
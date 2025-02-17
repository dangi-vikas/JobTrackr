package com.job.service.analytics.controller;

import com.job.service.analytics.service.AnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/analytics")
@Tag(name = "Job Application Analytics API", description = "APIs for analytics of a job application")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/status")
    @Operation(summary = "Get Application Status Count", description = "Returns a Map of status of job applications and their counts")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved status counts of job applications")
    public Map<String, Long> getApplicationStatusCount() {
        return analyticsService.getApplicationStatusCount();
    }
}
package com.job.service.analytics.service;

import com.job.service.analytics.repository.JobApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AnalyticsServiceTest {

    @Mock
    private JobApplicationRepository repository;

    @InjectMocks
    private AnalyticsService analyticsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetApplicationStatusCount() {
        when(repository.countByStatusIgnoreCase("Applied")).thenReturn(10L);
        when(repository.countByStatusIgnoreCase("Interviewed")).thenReturn(5L);
        when(repository.countByStatusIgnoreCase("Rejected")).thenReturn(3L);
        when(repository.countByStatusIgnoreCase("Offered")).thenReturn(2L);

        Map<String, Long> statusCount = analyticsService.getApplicationStatusCount();

        assertEquals(10L, statusCount.get("Applied"));
        assertEquals(5L, statusCount.get("Interviewed"));
        assertEquals(3L, statusCount.get("Rejected"));
        assertEquals(2L, statusCount.get("Offered"));

        verify(repository, times(1)).countByStatusIgnoreCase("Applied");
        verify(repository, times(1)).countByStatusIgnoreCase("Interviewed");
        verify(repository, times(1)).countByStatusIgnoreCase("Rejected");
        verify(repository, times(1)).countByStatusIgnoreCase("Offered");
    }
}
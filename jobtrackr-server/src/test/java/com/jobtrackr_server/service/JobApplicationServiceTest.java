package com.jobtrackr_server.service;

import com.jobtrackr_server.model.JobApplication;
import com.jobtrackr_server.repository.JobApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobApplicationServiceTest {

    @Mock
    private JobApplicationRepository repository;
    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private JobApplicationService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllApplications() {
        // Arrange
        JobApplication application1 = new JobApplication();
        application1.setId("1");
        application1.setJobTitle("Software Engineer");

        JobApplication application2 = new JobApplication();
        application2.setId("2");
        application2.setJobTitle("Data Scientist");

        when(repository.findAll()).thenReturn(Arrays.asList(application1, application2));

        List<JobApplication> applications = service.getAllApplications();

        assertEquals(2, applications.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testAddApplication() {
        JobApplication application = new JobApplication();
        application.setId("1");
        application.setJobTitle("Software Engineer");

        when(repository.save(application)).thenReturn(application);
        doNothing().when(kafkaProducerService).sendNotification(anyString());

        JobApplication savedApplication = service.addApplication(application);

        assertNotNull(savedApplication);
        assertEquals("Software Engineer", savedApplication.getJobTitle());
        verify(repository, times(1)).save(application);
        verify(kafkaProducerService, times(1)).sendNotification(anyString());
    }

    @Test
    void testGetApplicationsByUserId() {
        JobApplication application1 = new JobApplication();
        application1.setId("1");
        application1.setUserId("user1");
        application1.setJobTitle("Software Engineer");

        JobApplication application2 = new JobApplication();
        application2.setId("2");
        application2.setUserId("user1");
        application2.setJobTitle("Data Scientist");

        when(repository.findByUserId("user1")).thenReturn(Arrays.asList(application1, application2));

        List<JobApplication> applications = service.getApplicationsByUserId("user1");

        assertEquals(2, applications.size());
        verify(repository, times(1)).findByUserId("user1");
    }

    @Test
    void testGetApplicationsByStatus() {
        JobApplication application1 = new JobApplication();
        application1.setId("1");
        application1.setStatus("Applied");
        application1.setJobTitle("Software Engineer");

        JobApplication application2 = new JobApplication();
        application2.setId("2");
        application2.setStatus("Applied");
        application2.setJobTitle("Data Scientist");

        when(repository.findByStatusIgnoreCase("Applied")).thenReturn(Arrays.asList(application1, application2));

        List<JobApplication> applications = service.getApplicationsByStatus("Applied");

        assertEquals(2, applications.size());
        verify(repository, times(1)).findByStatusIgnoreCase("Applied");
    }

    @Test
    void testGetApplicationsByUserId_EmptyList() {
        when(repository.findByUserId("user1")).thenReturn(List.of());

        List<JobApplication> applications = service.getApplicationsByUserId("user1");

        assertTrue(applications.isEmpty());
        verify(repository, times(1)).findByUserId("user1");
    }
}

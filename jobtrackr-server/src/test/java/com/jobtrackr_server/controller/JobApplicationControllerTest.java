package com.jobtrackr_server.controller;

import com.jobtrackr_server.model.JobApplication;
import com.jobtrackr_server.service.JobApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class JobApplicationControllerTest {
    private MockMvc mockMvc;

    @Mock
    private JobApplicationService service;

    @InjectMocks
    private JobApplicationController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetAllApplications() throws Exception {
        JobApplication application1 = new JobApplication();
        application1.setId("1");
        application1.setJobTitle("Software Engineer");

        JobApplication application2 = new JobApplication();
        application2.setId("2");
        application2.setJobTitle("Data Scientist");

        List<JobApplication> applications = Arrays.asList(application1, application2);

        when(service.getAllApplications()).thenReturn(applications);

        mockMvc.perform(get("/api/v1/applications")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].jobTitle").value("Software Engineer"))
                .andExpect(jsonPath("$[1].jobTitle").value("Data Scientist"));

        verify(service, times(1)).getAllApplications();
    }

    @Test
    void testAddApplication() throws Exception {
        JobApplication application = new JobApplication();
        application.setId("1");
        application.setJobTitle("Software Engineer");

        when(service.addApplication(any(JobApplication.class))).thenReturn(application);

        String applicationJson = "{ \"jobTitle\": \"Software Engineer\" }";

        mockMvc.perform(post("/api/v1/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(applicationJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jobTitle").value("Software Engineer"));

        verify(service, times(1)).addApplication(any(JobApplication.class));
    }

    @Test
    void testGetApplicationsByUserId() throws Exception {
        JobApplication application1 = new JobApplication();
        application1.setId("1");
        application1.setUserId("user1");
        application1.setJobTitle("Software Engineer");

        JobApplication application2 = new JobApplication();
        application2.setId("2");
        application2.setUserId("user1");
        application2.setJobTitle("Data Scientist");

        List<JobApplication> applications = Arrays.asList(application1, application2);

        when(service.getApplicationsByUserId("user1")).thenReturn(applications);

        mockMvc.perform(get("/api/v1/applications/user/user1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].jobTitle").value("Software Engineer"))
                .andExpect(jsonPath("$[1].jobTitle").value("Data Scientist"));

        verify(service, times(1)).getApplicationsByUserId("user1");
    }

    @Test
    void testGetApplicationsByStatus() throws Exception {
        JobApplication application1 = new JobApplication();
        application1.setId("1");
        application1.setStatus("Applied");
        application1.setJobTitle("Software Engineer");

        JobApplication application2 = new JobApplication();
        application2.setId("2");
        application2.setStatus("Applied");
        application2.setJobTitle("Data Scientist");

        List<JobApplication> applications = Arrays.asList(application1, application2);

        when(service.getApplicationsByStatus("Applied")).thenReturn(applications);

        mockMvc.perform(get("/api/v1/applications/status/Applied")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].jobTitle").value("Software Engineer"))
                .andExpect(jsonPath("$[1].jobTitle").value("Data Scientist"));

        verify(service, times(1)).getApplicationsByStatus("Applied");
    }

    @Test
    void testGetApplicationsByUserId_EmptyList() throws Exception {
        when(service.getApplicationsByUserId("user1")).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/v1/applications/user/user1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());

        verify(service, times(1)).getApplicationsByUserId("user1");
    }
}

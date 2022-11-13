package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.repository.JobRepository;
import br.com.extratora.twelvekingdoms.service.impl.JobServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static br.com.extratora.twelvekingdoms.TestPayloads.UUID_1;
import static br.com.extratora.twelvekingdoms.TestPayloads.getJobModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class JobServiceTests {
    @Mock
    private JobRepository jobRepository;
    @InjectMocks
    private JobServiceImpl jobService;

    @Test
    void givenJobList_whenCalled_thenShouldCallRepository() {
        jobService.jobList();

        verify(jobRepository, times(1)).listBasicJobs();
    }

    @Test
    void givenGetLineageCalled_whenUUIDNotFound_thenShouldThrowDataNotFoundException() {
        when(jobRepository.findById(UUID_1)).thenReturn(Optional.empty());

        assertThrows(
                DataNotFoundException.class,
                () -> jobService.getJob(UUID_1)
        );

        verify(jobRepository, times(1)).findById(UUID_1);
    }

    @Test
    void givenGetLineageCalled_whenUUIDFound_thenShouldReturnData() {
        var jobModel = getJobModel();
        when(jobRepository.findById(UUID_1)).thenReturn(Optional.of(jobModel));

        var response = jobService.getJob(UUID_1);

        assertEquals(jobModel, response);
        verify(jobRepository, times(1)).findById(UUID_1);
    }
}

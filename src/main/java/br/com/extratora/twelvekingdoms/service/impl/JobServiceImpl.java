package br.com.extratora.twelvekingdoms.service.impl;

import br.com.extratora.twelvekingdoms.dto.IdNameDto;
import br.com.extratora.twelvekingdoms.exception.DataNotFoundException;
import br.com.extratora.twelvekingdoms.model.JobModel;
import br.com.extratora.twelvekingdoms.repository.JobRepository;
import br.com.extratora.twelvekingdoms.service.JobService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    @Cacheable(value = "jobs")
    public List<IdNameDto> jobList() {
        return jobRepository.listBasicJobs();
    }

    @Override
    @Cacheable(value = "jobs", key = "#id")
    public JobModel getJob(UUID id) {
        return jobRepository.findByIdEager(id).orElseThrow(DataNotFoundException::new);
    }
}

package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.dto.BasicIdNameDto;
import br.com.extratora.twelvekingdoms.model.JobModel;

import java.util.List;
import java.util.UUID;

public interface JobService {
    List<BasicIdNameDto> jobList();

    JobModel getJob(UUID id);
}

package br.com.extratora.twelvekingdoms.service;

import br.com.extratora.twelvekingdoms.dto.IdNameDto;
import br.com.extratora.twelvekingdoms.model.JobModel;

import java.util.List;
import java.util.UUID;

public interface JobService {
    List<IdNameDto> jobList();

    JobModel getJob(UUID id);
}

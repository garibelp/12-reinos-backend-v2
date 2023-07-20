package br.com.extratora.twelvekingdoms.controller.impl;

import br.com.extratora.twelvekingdoms.controller.JobController;
import br.com.extratora.twelvekingdoms.dto.response.IdNameListResponse;
import br.com.extratora.twelvekingdoms.model.JobModel;
import br.com.extratora.twelvekingdoms.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobControllerImpl implements JobController {
    private final JobService jobService;

    public JobControllerImpl(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<IdNameListResponse> jobList() {
        return ResponseEntity.ok(new IdNameListResponse(jobService.jobList()));
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<JobModel> jobDetails(@RequestParam UUID id) {
        return ResponseEntity.ok(jobService.getJob(id));
    }
}

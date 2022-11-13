package br.com.extratora.twelvekingdoms.repository;

import br.com.extratora.twelvekingdoms.dto.BasicIdNameDto;
import br.com.extratora.twelvekingdoms.model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<JobModel, UUID> {
    @Query(value = "SELECT new br.com.extratora.twelvekingdoms.dto.BasicIdNameDto(id, name) FROM JobModel")
    List<BasicIdNameDto> listBasicJobs();
}

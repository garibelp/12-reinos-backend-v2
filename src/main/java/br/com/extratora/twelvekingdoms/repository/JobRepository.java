package br.com.extratora.twelvekingdoms.repository;

import br.com.extratora.twelvekingdoms.dto.IdNameDto;
import br.com.extratora.twelvekingdoms.model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<JobModel, UUID> {
    @Query(value = "SELECT new br.com.extratora.twelvekingdoms.dto.IdNameDto(id, name) FROM JobModel")
    List<IdNameDto> listBasicJobs();

    @Query(value = "SELECT j FROM JobModel j INNER JOIN FETCH j.aptitudes WHERE j.id = ?1")
    Optional<JobModel> findByIdEager(UUID id);
}

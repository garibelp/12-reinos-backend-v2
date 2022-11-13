package br.com.extratora.twelvekingdoms.repository;

import br.com.extratora.twelvekingdoms.dto.BasicLineageDto;
import br.com.extratora.twelvekingdoms.model.LineageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LineageRepository extends JpaRepository<LineageModel, UUID> {
    @Query(value = "SELECT new br.com.extratora.twelvekingdoms.dto.BasicLineageDto(id, name) FROM LineageModel")
    List<BasicLineageDto> listBasicLineages();
}

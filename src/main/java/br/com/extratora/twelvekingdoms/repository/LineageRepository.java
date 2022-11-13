package br.com.extratora.twelvekingdoms.repository;

import br.com.extratora.twelvekingdoms.model.LineageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LineageRepository extends JpaRepository<LineageModel, UUID> {
}

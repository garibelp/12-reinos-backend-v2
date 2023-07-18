package br.com.extratora.twelvekingdoms.repository;

import br.com.extratora.twelvekingdoms.model.WoundsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WoundRepository extends JpaRepository<WoundsModel, UUID> {
}

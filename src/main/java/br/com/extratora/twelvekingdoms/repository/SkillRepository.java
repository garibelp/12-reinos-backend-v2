package br.com.extratora.twelvekingdoms.repository;

import br.com.extratora.twelvekingdoms.model.SkillModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SkillRepository extends JpaRepository<SkillModel, UUID> {
}

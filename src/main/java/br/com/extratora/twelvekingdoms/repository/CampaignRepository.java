package br.com.extratora.twelvekingdoms.repository;

import br.com.extratora.twelvekingdoms.dto.BasicCampaignDto;
import br.com.extratora.twelvekingdoms.model.CampaignModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CampaignRepository extends JpaRepository<CampaignModel, UUID> {
    @Query(value = "select c from CampaignModel c where c.id = ?1 and c.isActive = true")
    Optional<CampaignModel> findActiveById(UUID id);

    @Query(value = "select c from CampaignModel c inner join fetch c.sheets where c.id = ?1 and c.isActive = true")
    Optional<CampaignModel> findActiveByIdEager(UUID id);

    @Query(value = "select new br.com.extratora.twelvekingdoms.dto.BasicCampaignDto" +
            "(c.id, c.name) from CampaignModel c " +
            "where c.player.id = ?1",
            countQuery = "select count(*) from CampaignModel c where c.player.id = ?1")
    Page<BasicCampaignDto> findCampaignsPaginated(Pageable pageable, UUID playerId);

}

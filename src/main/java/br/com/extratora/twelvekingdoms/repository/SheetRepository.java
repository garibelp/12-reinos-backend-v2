package br.com.extratora.twelvekingdoms.repository;

import br.com.extratora.twelvekingdoms.dto.BasicSheetDto;
import br.com.extratora.twelvekingdoms.dto.CampaignSheetDto;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface SheetRepository extends JpaRepository<SheetModel, UUID> {
    String SHEET_PAGINATED_BASE_SELECT = "select new br.com.extratora.twelvekingdoms.dto.BasicSheetDto" +
            "(s.id, s.name, s.level, s.lineage.name as lineage, s.isActive as active, s.player.id, s.player.username as username)";

    String SHEET_DETAILS_BASE_SELECT = "select new br.com.extratora.twelvekingdoms.dto.CampaignSheetDto" +
            "(s.id, s.name, s.level, s.mentalCurrent, s.mentalTotal, s.physicalCurrent, s.physicalTotal, s.lineage.name, s.background.name)";

    @Query(value = SHEET_PAGINATED_BASE_SELECT + " from SheetModel s where s.player.id = ?1 and s.isActive = true and s.name like %?2%",
            countQuery = "select count(*) from SheetModel s where s.player.id = ?1 and s.isActive = true and s.name like %?2%")
    Page<BasicSheetDto> findActivePlayerSheetsPaginatedFilterByNameOpt(Pageable pageable, UUID playerId, String name);

    @Query(value = SHEET_PAGINATED_BASE_SELECT + " from SheetModel s where s.isActive = true and s.name like %?1%",
            countQuery = "select count(*) from SheetModel s where s.isActive = true and s.name like %?1%")
    Page<BasicSheetDto> findActiveSheetsPaginatedFilterByNameOpt(Pageable pageable, String name);

    @Query(value = SHEET_PAGINATED_BASE_SELECT + " from SheetModel s where s.name like %?1%",
            countQuery = "select count(*) from SheetModel s where s.name like %?1%")
    Page<BasicSheetDto> findSheetsPaginatedFilterByNameOpt(Pageable pageable, String name);

    @Query(value = "select s from SheetModel s inner join fetch s.aptitudes where s.id = ?1")
    Optional<SheetModel> findByIdEager(UUID id);

    @Query(value = "select count(*) from SheetModel s where s.id in ?1 and s.isActive = true")
    Long countActiveByIdIn(List<UUID> ids);

    @Query(value = SHEET_DETAILS_BASE_SELECT + " from SheetModel s where s.campaign.id = ?1 and s.isActive = true order by s.name asc")
    Set<CampaignSheetDto> findCampaignSheetByCampaignId(UUID campaignId);

}

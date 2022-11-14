package br.com.extratora.twelvekingdoms.repository;

import br.com.extratora.twelvekingdoms.dto.BasicSheetDto;
import br.com.extratora.twelvekingdoms.model.SheetModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SheetRepository extends JpaRepository<SheetModel, UUID> {
    String SHEET_PAGINATED_BASE_SELECT = "select new br.com.extratora.twelvekingdoms.dto.BasicSheetDto" +
            "(s.id, s.name, s.level, s.lineage.name as lineage, s.isActive as active, s.player.id, s.player.username as username)";

    @Query(value = SHEET_PAGINATED_BASE_SELECT + " from SheetModel s where s.player.id = ?1 and s.isActive = true",
            countQuery = "select count(*) from SheetModel s where s.player.id = ?1 and s.isActive = true")
    Page<BasicSheetDto> findActivePlayerSheetsPaginated(Pageable pageable, UUID playerId);

    @Query(value = SHEET_PAGINATED_BASE_SELECT + " from SheetModel s",
            countQuery = "select count(*) from SheetModel")
    Page<BasicSheetDto> findSheetsPaginated(Pageable pageable);

    @Query(value = "select s from SheetModel s inner join fetch s.aptitudes where s.id = ?1")
    Optional<SheetModel> findByIdEager(UUID id);
}

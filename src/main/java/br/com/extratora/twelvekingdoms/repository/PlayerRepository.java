package br.com.extratora.twelvekingdoms.repository;

import br.com.extratora.twelvekingdoms.dto.BasicPlayerDto;
import br.com.extratora.twelvekingdoms.model.PlayerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, UUID> {
    Optional<PlayerModel> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Query("update PlayerModel set isActive = false where id = ?1")
    void disableUser(UUID id);

    @Query(value = "SELECT new br.com.extratora.twelvekingdoms.dto.BasicPlayerDto(pm.id, pm.username, pm.email, pm.isActive) FROM PlayerModel pm",
            countQuery = "SELECT count(*) FROM PlayerModel")
    Page<BasicPlayerDto> listPaginated(Pageable pageable);
}

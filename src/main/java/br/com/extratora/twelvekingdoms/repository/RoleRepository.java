package br.com.extratora.twelvekingdoms.repository;

import br.com.extratora.twelvekingdoms.enums.Roles;
import br.com.extratora.twelvekingdoms.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleModel, UUID> {
    Optional<RoleModel> findByName(Roles name);
}

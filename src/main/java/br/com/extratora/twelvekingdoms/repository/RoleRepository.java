package br.com.extratora.twelvekingdoms.repository;

import br.com.extratora.twelvekingdoms.enums.RolesEnum;
import br.com.extratora.twelvekingdoms.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByName(RolesEnum name);
}

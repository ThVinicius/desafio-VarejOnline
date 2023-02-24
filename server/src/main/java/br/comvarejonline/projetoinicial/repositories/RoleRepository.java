package br.comvarejonline.projetoinicial.repositories;

import br.comvarejonline.projetoinicial.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {
}

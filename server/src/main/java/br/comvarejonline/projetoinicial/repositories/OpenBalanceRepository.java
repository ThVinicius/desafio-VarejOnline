package br.comvarejonline.projetoinicial.repositories;

import br.comvarejonline.projetoinicial.models.OpenBalanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenBalanceRepository extends JpaRepository<OpenBalanceModel, Long> {
}

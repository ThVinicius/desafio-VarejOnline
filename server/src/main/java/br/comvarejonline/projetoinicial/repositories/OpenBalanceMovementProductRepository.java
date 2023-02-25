package br.comvarejonline.projetoinicial.repositories;

import br.comvarejonline.projetoinicial.models.OpenBalanceMovementProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenBalanceMovementProductRepository extends JpaRepository<OpenBalanceMovementProduct, Long> {
}

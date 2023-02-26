package br.comvarejonline.projetoinicial.repositories;

import br.comvarejonline.projetoinicial.dto.response.ProductInfo;
import br.comvarejonline.projetoinicial.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    @Query("SELECT p.productId AS productId, p.name AS name, p.barCode AS barCode, " +
            "p.minimumAmount as minimumAmount, p.registrationDate as registrationDate, " +
            "o.amount AS openBalance " +
            "FROM ProductModel p " +
            "JOIN OpenBalanceModel o ON o.product.productId = p.productId")
    List<ProductInfo> findAllWithInfo();
}

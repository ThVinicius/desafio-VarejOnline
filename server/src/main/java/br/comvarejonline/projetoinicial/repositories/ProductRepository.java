package br.comvarejonline.projetoinicial.repositories;

import br.comvarejonline.projetoinicial.dto.response.ProductInfo;
import br.comvarejonline.projetoinicial.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    @Query("SELECT p.id AS productId, p.name AS name, p.barCode AS barCode, " +
            "p.minimumAmount as minimumAmount, p.registrationDate as registrationDate, " +
            "o.amount AS openBalance " +
            "FROM ProductModel p " +
            "JOIN OpenBalanceModel o ON o.product.id = p.id")
    List<ProductInfo> findAllWithInfo();

    @Query(nativeQuery = true, value = "SELECT " +
            "coalesce((SELECT sum(amount) FROM open_balance WHERE product_id = :productId), 0) + " +
            "coalesce((SELECT sum(amount) FROM input_balance WHERE product_id = :productId), 0) + " +
            "coalesce((SELECT sum(amount) FROM input_adjust_balance WHERE product_id = :productId), 0) - " +
            "coalesce((SELECT sum(amount) FROM output_balance WHERE product_id = :productId), 0) - " +
            "coalesce((SELECT sum(amount) FROM output_adjust_balance WHERE product_id = :productId), 0) AS totalBalance")
    Long totalBalance(@Param("productId") Long productId);
}

package br.comvarejonline.projetoinicial.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "open_balance_movement_products")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class OpenBalanceMovementProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long openBalanceMovementProductId;

    @NonNull
    @Column(nullable = false)
    private Long amount;

    @NonNull
    @OneToOne
    @JoinColumn(name = "productId")
    private ProductModel product;
}

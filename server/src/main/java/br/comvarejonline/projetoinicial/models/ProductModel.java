package br.comvarejonline.projetoinicial.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false, unique = true)
    private String barCode;

    @NonNull
    @Column(nullable = false)
    private Long minimumAmount;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @OneToOne(mappedBy = "product")
    private OpenBalanceModel openBalanceMovementProduct;

    @PrePersist
    public void prePersist() {
        this.registrationDate = LocalDateTime.now();
    }

}

package br.comvarejonline.projetoinicial.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "open_balance")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class OpenBalanceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long openBalanceId;

    @NonNull
    @Column(nullable = false)
    private Long amount;

    @NonNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private ProductModel product;
}

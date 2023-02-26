package br.comvarejonline.projetoinicial.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "input_adjust_balance")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class InputAdjustBalanceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private Long amount;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime movementDate;

    @NonNull
    @Column(nullable = false)
    private String reason;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private ProductModel product;
}

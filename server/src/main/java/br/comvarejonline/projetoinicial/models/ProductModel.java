package br.comvarejonline.projetoinicial.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private OpenBalanceModel openBalance;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<InputBalanceModel> inputsBalance;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OutputBalanceModel> outputsBalance;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<InputAdjustBalanceModel> inputsAdjustBalance;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OutputAdjustBalanceModel> outputsAdjustBalance;

    @PrePersist
    public void prePersist() {

        this.registrationDate = LocalDateTime
                .now()
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

}

package br.comvarejonline.projetoinicial.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;

    private String name;

    private String barCode;

    private Long minimumAmount;

    private Long openBalance;

    private LocalDateTime registrationDate;
}

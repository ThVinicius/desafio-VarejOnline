package br.comvarejonline.projetoinicial.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long productId;

    private String name;

    private String barCode;

    private Long minimumAmount;

    private Long openBalance;
}

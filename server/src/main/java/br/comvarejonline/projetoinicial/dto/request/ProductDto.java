package br.comvarejonline.projetoinicial.dto.request;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {

    @NotBlank
    private String name;

    @NotBlank
    private String barCode;
    @NotNull
    @Min(0)
    private Long minimumAmount;

    @NotNull
    @Min(0)
    private Long openBalanceMovement;

    @AssertTrue(message = "openBalanceMovement deve ser maior ou igual à minimumAmount")
    public boolean isAmountValidate() {
        return this.openBalanceMovement >= this.minimumAmount;
    }
}

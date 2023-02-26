package br.comvarejonline.projetoinicial.dto.response;

import java.time.LocalDateTime;

public interface ProductInfo {
    Long getProductId();

    String getName();

    String getBarCode();

    Long getMinimumAmount();

    Long getOpenBalance();

    LocalDateTime getRegistrationDate();
}

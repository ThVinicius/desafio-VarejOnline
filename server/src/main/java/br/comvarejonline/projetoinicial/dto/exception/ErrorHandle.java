package br.comvarejonline.projetoinicial.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorHandle {
    private String field;
    private String message;

}

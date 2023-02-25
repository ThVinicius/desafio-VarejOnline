package br.comvarejonline.projetoinicial.exceptions;

import br.comvarejonline.projetoinicial.dto.exception.ErrorHandle;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorHandle> validationHandle(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrorList =
                exception.getBindingResult().getFieldErrors();

        List<ErrorHandle> list = new ArrayList<>();
        fieldErrorList.forEach(error -> {
            String field = error.getField();
            String message = error.getDefaultMessage();
            list.add(new ErrorHandle(field, message));
        });


        return list;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected String handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return "Esse código de barras (barCode) já está cadastrado";
    }
}

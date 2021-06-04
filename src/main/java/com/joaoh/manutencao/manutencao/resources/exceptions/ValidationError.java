package com.joaoh.manutencao.manutencao.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private List<FieldMessageError> errors = new ArrayList<>();
    

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessageError> getErrors() {
        return errors;
    }

    public void addFieldError(String field, String message) {
        this.errors.add(new FieldMessageError(field, message));
    }
}

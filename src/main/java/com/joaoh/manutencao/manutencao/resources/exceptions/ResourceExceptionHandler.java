package com.joaoh.manutencao.manutencao.resources.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import com.joaoh.manutencao.manutencao.services.exceptions.DataIntegrityException;
import com.joaoh.manutencao.manutencao.services.exceptions.ObjectNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Objeto não encontrado", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    };

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException exception, HttpServletRequest request) {
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Não é possível deletar pois há entidades relacionadas", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    };

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationError(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", "Erros nos campos digitados", request.getRequestURI()
        );

        for (FieldError err : e.getFieldErrors()) {
            error.addFieldError(err.getField(), err.getDefaultMessage());
        }

        return ResponseEntity.unprocessableEntity().body(error);
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<ValidationError> validationError(javax.validation.ConstraintViolationException e, HttpServletRequest request) {
        ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", "Erro nos campos digitados", request.getRequestURI()
        );

        for (ConstraintViolation<?> err : e.getConstraintViolations()) {
            error.addFieldError(err.getPropertyPath().toString(), err.getMessage());
        }

        return ResponseEntity.unprocessableEntity().body(error);
    }
}

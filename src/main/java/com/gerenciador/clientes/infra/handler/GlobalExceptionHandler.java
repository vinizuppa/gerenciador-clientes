package com.gerenciador.clientes.infra.handler;

import com.gerenciador.clientes.infra.exceptions.HttpException;
import com.gerenciador.clientes.infra.models.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingMatrixVariableException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {//Intercepta todas exceções mapeadas aqui

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorModel> exception(final Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()));
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorModel> httpException(final HttpException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(new ErrorModel(exception.getCode(), exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorModel> methodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        ErrorModel errorModel = new ErrorModel();
        exception.getBindingResult().getFieldErrors().forEach(fieldError ->
                errorModel.addError(HttpStatus.BAD_REQUEST.value(), fieldError.getField() + " " + fieldError.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);
    }

    @ExceptionHandler({ MissingPathVariableException.class, MissingMatrixVariableException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class })
    public ResponseEntity<ErrorModel> genericBadRequestException(final Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorModel(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }
}

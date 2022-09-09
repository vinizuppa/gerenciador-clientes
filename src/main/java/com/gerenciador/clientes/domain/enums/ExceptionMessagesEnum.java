package com.gerenciador.clientes.domain.enums;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public enum ExceptionMessagesEnum {


    //404
    CIDADE_NOT_FOUND(404000, "Cidade não encontrada!", HttpStatus.NOT_FOUND),

    UF_NOT_FOUND(404001, "UF não encontrada!", HttpStatus.NOT_FOUND),

    USUARIO_NOT_FOUND(404002, "Usuario não encontrado!", HttpStatus.NOT_FOUND);



    private final Integer code;
    private final String message;
    private final HttpStatus httpStatus;

    ExceptionMessagesEnum(Integer code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
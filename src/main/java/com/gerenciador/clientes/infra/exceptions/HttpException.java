package com.gerenciador.clientes.infra.exceptions;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private final HttpStatus httpStatus;

    public HttpException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpException(final Integer code, final String message, final HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public Integer getCode() {
        if (code != null) {
            return code;
        }
        return httpStatus.value();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

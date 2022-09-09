package com.gerenciador.clientes.infra.exceptions;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundException extends HttpException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(final String message) {
        super(message, NOT_FOUND);
    }

    public NotFoundException(final HttpStatus code, String message) {
        super(code.value(), message, code);
    }

}

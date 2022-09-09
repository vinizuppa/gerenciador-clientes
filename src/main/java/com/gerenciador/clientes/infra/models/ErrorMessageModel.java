package com.gerenciador.clientes.infra.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "code", "message" })
public class ErrorMessageModel {

    private Integer code;
    private String message;

    public ErrorMessageModel(final Integer code, final String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("ErrorMessage{code=%s,message='%s'}", this.getCode(), this.getMessage());
    }

}

package com.gerenciador.clientes.api.rest.models.Cidade;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CidadeUpdateRequest {
    private Integer cidadeId;

    @NotBlank
    @Size(min = 3, max = 150)
    private String nome;

    @Valid
    private Integer ufId;
}

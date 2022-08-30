package com.gerenciador.clientes.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //Todas vezes que for serializar o objeto para JSON, será oculto objetos com valores nulos.
public class CidadeDto {

    private Integer cidadeId;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(min = 3, max = 150)
    private String nome;

    private Integer ufId;

}

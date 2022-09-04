package com.gerenciador.clientes.models.Uf;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//Todas vezes que for serializar o objeto para JSON, será oculto objetos com valores nulos.
public class UfResponse {

    private Integer id;
    private String nome;
}

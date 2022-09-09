package com.gerenciador.clientes.api.rest.models.Cidade;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //Todas vezes que for serializar o objeto para JSON, será oculto objetos com valores nulos.
public class CidadeResponse {

    private Integer id;

    private String nome;
    private Uf uf;

    public void setUf(com.gerenciador.clientes.domain.entities.Uf uf) {
        this.uf = new Uf();
        this.uf.nome = uf.getNome();
        this.uf.id = uf.getId();
    }


    @Data
    public static class Uf {
        private String nome;
        private Integer id;
    }

}

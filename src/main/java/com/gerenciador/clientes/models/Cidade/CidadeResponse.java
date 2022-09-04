package com.gerenciador.clientes.models.Cidade;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gerenciador.clientes.entities.Uf;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //Todas vezes que for serializar o objeto para JSON, será oculto objetos com valores nulos.
public class CidadeResponse {

    private Integer id;

    private String nome;
    private Uf uf;

    public void setUf(com.gerenciador.clientes.entities.Uf uf) {
        this.uf = new Uf();
        this.uf.nome = uf.getNome();

    }


    @Data
    public static class Uf {
        private String nome;

    }

}

package com.gerenciador.clientes.models.Usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gerenciador.clientes.enums.ClienteStatus;
import com.gerenciador.clientes.enums.TipoDocumento;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //Todas vezes que for serializar o objeto para JSON, será oculto objetos com valores nulos.
public class UsuarioResponse {

    private Integer id;

    private String nome;

    private String documento;

    private String email;

    private String telefone;

    private String celular;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataCadastro;

    private ClienteStatus clienteStatus;

    private TipoDocumento tipoDocumento;
    private Endereco endereco;

    public void setEndereco(com.gerenciador.clientes.entities.Endereco endereco) {
        this.endereco = new Endereco();
        this.endereco.rua = endereco.getRua();
        this.endereco.numero = endereco.getNumero();
        this.endereco.bairro = endereco.getBairro();
        this.endereco.cep = endereco.getCep();
        this.endereco.cidadeId = endereco.getCidade().getId();

    }

    @Data
    public static class Endereco {

        private String rua;

        private String numero;

        private String bairro;

        private String cep;

        private Integer cidadeId;

    }
}

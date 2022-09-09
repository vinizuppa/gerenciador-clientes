package com.gerenciador.clientes.api.rest.models.Usuario;

import com.gerenciador.clientes.domain.enums.TipoDocumento;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UsuarioRequest {

    private Integer usuarioId;

    @NotNull
    @Size(min = 3, max = 150)
    private String nome;

    @NotNull
    @Size(min = 11, max = 14)
    private String documento;

    @Size(min = 6, max = 20)
    @NotNull
    private String senha;

    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min =10 ,max =13)
    private String telefone;

    @NotNull
    @Size(min = 11, max = 14)
    private String celular;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Valid
    @NotNull
    private Endereco endereco;

    @Data
    public static class Endereco {

        @NotNull
        @Size(min = 3, max = 50)
        private String rua;

        @NotNull
        @Size(min = 1, max = 4)
        private String numero;

        @NotNull
        @Size(min = 3, max = 50)
        private String bairro;

        @NotNull
        @Size(min = 8, max = 9)
        private String cep;

        @NotNull
        private Integer cidadeId;

    }
}

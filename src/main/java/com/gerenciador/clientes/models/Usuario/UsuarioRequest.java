package com.gerenciador.clientes.models.Usuario;

import com.gerenciador.clientes.enums.TipoDocumento;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UsuarioRequest {

    private Integer usuarioId;

    @NotBlank
    @Size(min = 3, max = 150)
    private String nome;

    @NotBlank
    @Size(min = 10, max = 50)
    private String documento;

    @Size(min = 6, max = 50)
    @NotBlank
    private String senha;

    @Email
    @NotBlank
    private String email;

    @Size(min =10 ,max =13)
    private String telefone;

    @Size(min = 11, max = 14)
    private String celular;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Valid
    private Endereco endereco;

    @Data
    public static class Endereco {

        @Size(min = 3, max = 50)
        private String rua;

        @Size(min = 1, max = 4)
        private String numero;

        @Size(min = 3, max = 50)
        private String bairro;

        private String cep;

        private Integer cidadeId;

    }
}

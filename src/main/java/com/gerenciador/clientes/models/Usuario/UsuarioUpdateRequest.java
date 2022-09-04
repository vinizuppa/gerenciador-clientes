package com.gerenciador.clientes.models.Usuario;

import com.gerenciador.clientes.enums.ClienteStatus;
import com.gerenciador.clientes.enums.TipoDocumento;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UsuarioUpdateRequest {
    private Integer usuarioId;


    @NotBlank
    @Size(min = 3, max = 150)
    private String nome;

    @NotBlank
    @Size(min = 10, max = 50)
    private String documento;

    @Email
    @NotBlank
    private String email;

    @Size(min =10 ,max =13)
    private String telefone;

    @Size(min = 11, max = 14)
    private String celular;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ClienteStatus clienteStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @Valid
    private UsuarioUpdateRequest.Endereco endereco;

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

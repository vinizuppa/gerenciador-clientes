package com.gerenciador.clientes.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gerenciador.clientes.enums.TipoDocumento;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //Todas vezes que for serializar o objeto para JSON, será oculto objetos com valores nulos.
public class UsuarioDto {

    private Integer usuarioId;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(min = 3, max = 150)
    private String nome;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(min = 10, max = 50)
    private String documento;

    @Email
    @NotBlank(message = "Preenchimento obrigatório")
    private String email;

    @Size(min = 5, max = 150)
    private String endereco;

    @Size(min = 1, max = 5)
    private String numero;

    @Size(min = 5, max = 150)
    private String bairro;

    @Size(min = 10, max = 25)
    private String cep;

    private Integer cidadeId;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Preenchimento obrigatório")
    private TipoDocumento tipoDocumento;
}

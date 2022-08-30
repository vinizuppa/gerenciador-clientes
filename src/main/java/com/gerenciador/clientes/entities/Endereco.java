package com.gerenciador.clientes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //Todas vezes que for serializar o objeto para JSON, será oculto objetos com valores nulos.
@Entity
@Table(name = "ENDERECO")
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(length = 150)
    private String endereco;

    @Column(length = 5)
    private String numero;

    @Column(length = 150)
    private String bairro;

    @Column(length = 25)
    private String cep;

    @ManyToOne
    private Cidade cidade;

    @JsonIgnore
    @ManyToOne
    private Usuario usuario;

}

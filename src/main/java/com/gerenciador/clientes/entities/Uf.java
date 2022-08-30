package com.gerenciador.clientes.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //Todas vezes que for serializar o objeto para JSON, será oculto objetos com valores nulos.
@Entity
@Table(name = "UF")
public class Uf implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false, length = 2)
    private String nome;

    @OneToMany(mappedBy = "uf", cascade=CascadeType.ALL)// cascade=CascadeType.ALL serve para indicar que se for apagar a uf do banco deve ser apagado as cidades dele também.
    private List<Cidade> cidades = new ArrayList<>();
}

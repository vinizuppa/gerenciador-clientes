package com.gerenciador.clientes.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ENDERECO")
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 150)
    private String rua;

    @Column(length = 5)
    private String numero;

    @Column(length = 150)
    private String bairro;

    @Column(length = 25)
    private String cep;

    @ManyToOne
    private Cidade cidade;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}

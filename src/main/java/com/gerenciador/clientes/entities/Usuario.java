package com.gerenciador.clientes.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gerenciador.clientes.enums.ClienteStatus;
import com.gerenciador.clientes.enums.TipoDocumento;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //Todas vezes que for serializar o objeto para JSON, será oculto objetos com valores nulos.
@Entity
@Table(name = "USUARIO")
public class Usuario extends RepresentationModel<Usuario> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 50)
    private String documento;

    @Column(length = 50)
    @JsonIgnore
    private String senha;

    @Column(unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") //Retorna data no formato desejado
    private LocalDate dataCadastro;

    @OneToMany(mappedBy = "usuario", cascade=CascadeType.ALL)// cascade=CascadeType.ALL serve para indicar que se for apagar o cliente do banco deve ser apagado os endereços dele também.
    private List<Endereco> enderecos = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClienteStatus Status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;


}

package com.gerenciador.clientes.entities;

import com.gerenciador.clientes.enums.ClienteStatus;
import com.gerenciador.clientes.enums.TipoDocumento;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;

@Data
@Entity
@Table(name = "USUARIO")
public class Usuario extends RepresentationModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 50)
    private String documento;

    @Column(nullable = false, length = 50)
    private String senha;

    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 13)
    private String telefone;

    @Column(length = 14)
    private String celular;
    @Column(nullable = false)
    private LocalDate dataCadastro;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClienteStatus clienteStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @PrePersist
    private void prePersist() {
        dataCadastro = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        clienteStatus = ClienteStatus.ATIVO;
    }


}

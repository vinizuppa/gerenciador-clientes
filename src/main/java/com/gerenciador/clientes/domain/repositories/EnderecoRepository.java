package com.gerenciador.clientes.domain.repositories;

import com.gerenciador.clientes.domain.entities.Endereco;
import com.gerenciador.clientes.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    Optional<Endereco> findByUsuario(Usuario usuario);
}

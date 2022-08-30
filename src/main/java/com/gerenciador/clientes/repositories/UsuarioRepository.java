package com.gerenciador.clientes.repositories;

import com.gerenciador.clientes.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByDocumento(String documento);
}

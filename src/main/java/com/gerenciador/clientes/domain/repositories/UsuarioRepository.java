package com.gerenciador.clientes.domain.repositories;

import com.gerenciador.clientes.domain.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario> {

    Optional<Usuario> findByDocumento(String documento);
    Page<Usuario> findAll(Specification<Usuario> spec, Pageable pageable);
}

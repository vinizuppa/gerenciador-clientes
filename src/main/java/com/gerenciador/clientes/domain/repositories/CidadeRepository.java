package com.gerenciador.clientes.domain.repositories;

import com.gerenciador.clientes.domain.entities.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    Page<Cidade> findAll(Pageable pageable);

    Optional<Cidade> findByNome(String nome);
}

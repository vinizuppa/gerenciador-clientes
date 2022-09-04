package com.gerenciador.clientes.repositories;

import com.gerenciador.clientes.entities.Cidade;
import com.gerenciador.clientes.entities.Uf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UfRepository extends JpaRepository<Uf, Integer>{
    Page<Uf> findAll(Pageable pageable);
}

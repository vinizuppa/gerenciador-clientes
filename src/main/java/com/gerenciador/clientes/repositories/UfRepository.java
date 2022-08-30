package com.gerenciador.clientes.repositories;

import com.gerenciador.clientes.entities.Uf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UfRepository extends JpaRepository<Uf, Integer>{
    Page<Uf> findAll(Pageable pageable);
}

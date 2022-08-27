package com.gerenciador.clientes.repositories;

import com.gerenciador.clientes.models.CidadeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<CidadeModel, Integer> {
}

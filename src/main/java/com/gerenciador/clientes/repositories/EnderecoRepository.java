package com.gerenciador.clientes.repositories;

import com.gerenciador.clientes.models.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoModel, Integer> {
}

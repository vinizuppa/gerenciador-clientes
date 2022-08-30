package com.gerenciador.clientes.repositories;

import com.gerenciador.clientes.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}

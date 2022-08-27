package com.gerenciador.clientes.repositories;

import com.gerenciador.clientes.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteModel, Integer> {

    Optional<ClienteModel> findByDocumento(String documento);
}

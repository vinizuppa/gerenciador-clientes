package com.gerenciador.clientes.services;

import com.gerenciador.clientes.entities.Uf;
import com.gerenciador.clientes.repositories.UfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UfService {
    @Autowired
    UfRepository ufRepository;

    //Metodo para buscar todas Uf
    public Page<Uf> findAll(Pageable pageable){
        return ufRepository.findAll(pageable);
    }

    //Metodo para buscar Uf por Id
    public Optional<Uf> findById(Integer ufId){
        return ufRepository.findById(ufId);
    }
}

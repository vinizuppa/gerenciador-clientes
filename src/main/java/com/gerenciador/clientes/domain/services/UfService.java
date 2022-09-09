package com.gerenciador.clientes.domain.services;

import com.gerenciador.clientes.domain.entities.Uf;
import com.gerenciador.clientes.domain.repositories.CidadeRepository;
import com.gerenciador.clientes.domain.repositories.UfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UfService {
    @Autowired
    UfRepository ufRepository;

    @Autowired
    CidadeRepository cidadeRepository;

    //Metodo para buscar todas Uf
    public List<Uf> findAll(){
        return ufRepository.findAll();
    }

    //Metodo para buscar Uf por Id
    public Optional<Uf> findById(Integer ufId){
        return ufRepository.findById(ufId);
    }

}

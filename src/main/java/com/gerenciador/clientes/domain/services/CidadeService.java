package com.gerenciador.clientes.domain.services;

import com.gerenciador.clientes.domain.entities.Cidade;
import com.gerenciador.clientes.domain.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
    @Autowired
    CidadeRepository cidadeRepository;

    //Metodo para buscar todas Cidades
    public List<Cidade> findAll(){
        return cidadeRepository.findAll();
    }

    //Metodo para buscar cidade pelo id
    public Optional<Cidade> findById(Integer cidadeid) {
        return cidadeRepository.findById(cidadeid);
    }

    //Metodo para buscar cidade pelo nome
    public Optional<Cidade> findByNome(String nome){
        return cidadeRepository.findByNome(nome);
    }

    //Metodo para salvar Cidade
    public Cidade save(Cidade cidade){
        return cidadeRepository.save(cidade);
    }

    public Cidade updateCidade(Cidade cidade) {
        return this.save(cidade);
    }
}

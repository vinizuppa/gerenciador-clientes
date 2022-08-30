package com.gerenciador.clientes.services;

import com.gerenciador.clientes.entities.Endereco;
import com.gerenciador.clientes.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository enderecoRepository;

    //Metodo para salvar endereco
    public Endereco save(Endereco endereco){

        return enderecoRepository.save(endereco);
    }
}

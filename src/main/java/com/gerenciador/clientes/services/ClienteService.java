package com.gerenciador.clientes.services;

import com.gerenciador.clientes.models.ClienteModel;
import com.gerenciador.clientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    //Metodo para buscar todos clientes
    public List<ClienteModel> findAll(){
        return clienteRepository.findAll();
    }

    //Metodo para buscar cliente pelo documento
    public Optional<ClienteModel> findByDocumento(String documento){
        return clienteRepository.findByDocumento(documento);
    }

    //Metodo para salvar cliente
    public ClienteModel save(ClienteModel clienteModel){
        return clienteRepository.save(clienteModel);
    }

    //Metodo para atualizar cliente
    public ClienteModel updateCliente(ClienteModel clienteModel){
        clienteModel = this.save(clienteModel);//Salva cliente com as atualizações no banco
        return clienteModel;
    }

    //Metodo para atualizar senha do cliente
    public ClienteModel updateSenha(ClienteModel clienteModel){
        return save(clienteModel);
    }


}

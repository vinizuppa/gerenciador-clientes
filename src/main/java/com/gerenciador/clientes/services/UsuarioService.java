package com.gerenciador.clientes.services;

import com.gerenciador.clientes.entities.Usuario;
import com.gerenciador.clientes.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    //Metodo para buscar todos clientes
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    //Metodo para buscar cliente pelo id
    public Optional<Usuario> findById(Integer usuarioId) {
        return usuarioRepository.findById(usuarioId);
    }

    //Metodo para buscar cliente pelo documento
    public Optional<Usuario> findByDocumento(String documento){
        return usuarioRepository.findByDocumento(documento);
    }

    //Metodo para salvar cliente
    public Usuario save(Usuario usuario){

        return usuarioRepository.save(usuario);
    }

    //Metodo para atualizar cliente
    public Usuario updateCliente(Usuario usuario){
        usuario = this.save(usuario);//Salva cliente com as atualizações no banco
        return usuario;
    }

    //Metodo para atualizar senha do cliente
    public Usuario updateSenha(Usuario usuario){
        return save(usuario);
    }



}

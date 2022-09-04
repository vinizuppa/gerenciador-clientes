package com.gerenciador.clientes.services;

import com.gerenciador.clientes.entities.Endereco;
import com.gerenciador.clientes.entities.Usuario;
import com.gerenciador.clientes.models.Usuario.UsuarioRequest;
import com.gerenciador.clientes.models.Usuario.UsuarioUpdateRequest;
import com.gerenciador.clientes.repositories.EnderecoRepository;
import com.gerenciador.clientes.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    EnderecoService enderecoService;

    //Metodo para buscar todos clientes com paginação
    public Page<Usuario> findAll(Specification<Usuario> spec, Pageable pageable){
        return usuarioRepository.findAll(spec, pageable);
    }

    //Metodo para buscar cliente pelo id
    public Optional<Usuario> findById(Integer usuarioId) {
        return usuarioRepository.findById(usuarioId);
    }

    //Metodo para buscar cliente pelo documento
    public Optional<Usuario> findByDocumento(String documento){
        return usuarioRepository.findByDocumento(documento);
    }

    //Metodo para salvar usuario
    @Transactional
    public Usuario save(Usuario usuario, UsuarioRequest usuarioRequest){

       usuario = usuarioRepository.save(usuario);

       enderecoService.save(usuario, usuarioRequest);

        return usuario;
    }

    public Usuario save(Usuario usuario){

        return usuarioRepository.save(usuario);
    }

    //Metodo para atualizar cliente
    @Transactional
    public Usuario updateUsuario(Usuario usuario, UsuarioUpdateRequest usuarioUpdateRequest){
        this.save(usuario);
        enderecoService.update(usuario, usuarioUpdateRequest);
        return usuario;
    }

}

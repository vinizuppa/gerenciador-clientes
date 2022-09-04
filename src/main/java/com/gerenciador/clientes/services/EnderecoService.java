package com.gerenciador.clientes.services;

import com.gerenciador.clientes.converters.EnderecoConverter;
import com.gerenciador.clientes.entities.Cidade;
import com.gerenciador.clientes.entities.Endereco;
import com.gerenciador.clientes.entities.Usuario;
import com.gerenciador.clientes.models.Usuario.UsuarioRequest;
import com.gerenciador.clientes.models.Usuario.UsuarioUpdateRequest;
import com.gerenciador.clientes.repositories.CidadeRepository;
import com.gerenciador.clientes.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    EnderecoConverter enderecoConverter;

    @Autowired
    CidadeRepository cidadeRepository;

    //Metodo para salvar endereco
    public Endereco save(Usuario usuario, UsuarioRequest usuarioRequest) {
        Cidade cidade = cidadeRepository.findById(usuarioRequest.getEndereco().getCidadeId()).orElseThrow();
        Endereco endereco = enderecoConverter.toEntity(usuario, usuarioRequest, cidade);

        return enderecoRepository.save(endereco);
    }

    //Metodo para atualizar
    @Transactional
    public Endereco update(Usuario usuario, UsuarioUpdateRequest usuarioUpdateRequest) {
        Cidade cidade = cidadeRepository.findById(usuarioUpdateRequest.getEndereco().getCidadeId()).orElseThrow();
        Optional<Endereco> enderecoOptional = enderecoRepository.findByUsuario(usuario);
        Endereco endereco = enderecoConverter.toEntityUpdate(usuario, usuarioUpdateRequest, cidade, enderecoOptional.get());

        return enderecoRepository.save(endereco);
    }

    public Optional<Endereco> findByUsuario(Usuario usuario) {
        return enderecoRepository.findByUsuario(usuario);
    }
}

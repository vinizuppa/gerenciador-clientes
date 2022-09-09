package com.gerenciador.clientes.domain.services;

import com.gerenciador.clientes.api.rest.converters.EnderecoConverter;
import com.gerenciador.clientes.domain.entities.Cidade;
import com.gerenciador.clientes.domain.entities.Endereco;
import com.gerenciador.clientes.domain.entities.Usuario;
import com.gerenciador.clientes.api.rest.models.Usuario.UsuarioRequest;
import com.gerenciador.clientes.api.rest.models.Usuario.UsuarioUpdateRequest;
import com.gerenciador.clientes.domain.repositories.CidadeRepository;
import com.gerenciador.clientes.domain.repositories.EnderecoRepository;
import com.gerenciador.clientes.infra.exceptions.NotFoundException;
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
        Cidade cidade = cidadeRepository.findById(usuarioRequest.getEndereco().getCidadeId()).orElseThrow(() -> new NotFoundException("Cidade não encontrada!"));
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

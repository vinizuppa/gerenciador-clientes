package com.gerenciador.clientes.api.rest.converters;

import com.gerenciador.clientes.domain.entities.Cidade;
import com.gerenciador.clientes.domain.entities.Endereco;
import com.gerenciador.clientes.domain.entities.Usuario;
import com.gerenciador.clientes.api.rest.models.Usuario.UsuarioRequest;
import com.gerenciador.clientes.api.rest.models.Usuario.UsuarioUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class EnderecoConverter {

    public Endereco toEntity(Usuario usuario, UsuarioRequest usuarioRequest, Cidade cidade) {
        Endereco endereco = new Endereco();
        endereco.setRua(usuarioRequest.getEndereco().getRua());
        endereco.setNumero(usuarioRequest.getEndereco().getNumero());
        endereco.setBairro(usuarioRequest.getEndereco().getBairro());
        endereco.setCep(usuarioRequest.getEndereco().getCep());

        endereco.setCidade(cidade);
        endereco.setUsuario(usuario);

        return endereco;
    }

    public Endereco toEntityUpdate(Usuario usuario, UsuarioUpdateRequest usuarioUpdateRequest, Cidade cidade, Endereco enderecoEntity) {
        Endereco endereco = new Endereco();
        endereco.setId(enderecoEntity.getId());
        endereco.setRua(usuarioUpdateRequest.getEndereco().getRua());
        endereco.setNumero(usuarioUpdateRequest.getEndereco().getNumero());
        endereco.setBairro(usuarioUpdateRequest.getEndereco().getBairro());
        endereco.setCep(usuarioUpdateRequest.getEndereco().getCep());
        endereco.setCidade(cidade);
        endereco.setUsuario(usuario);

        return endereco;
    }
}

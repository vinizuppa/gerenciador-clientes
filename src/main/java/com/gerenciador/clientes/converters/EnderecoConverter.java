package com.gerenciador.clientes.converters;

import com.gerenciador.clientes.entities.Cidade;
import com.gerenciador.clientes.entities.Endereco;
import com.gerenciador.clientes.entities.Usuario;
import com.gerenciador.clientes.models.Usuario.UsuarioRequest;
import com.gerenciador.clientes.models.Usuario.UsuarioUpdateRequest;
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

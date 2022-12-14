package com.gerenciador.clientes.api.rest.converters;

import com.gerenciador.clientes.domain.entities.Endereco;
import com.gerenciador.clientes.domain.entities.Usuario;
import com.gerenciador.clientes.api.rest.models.Usuario.UsuarioRequest;
import com.gerenciador.clientes.api.rest.models.Usuario.UsuarioResponse;
import com.gerenciador.clientes.api.rest.models.Usuario.UsuarioUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter {//Classe para converter e não expor a entidade na requisição

    public UsuarioResponse toModel(Usuario usuario, Endereco endereco) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setId(usuario.getId());
        usuarioResponse.setNome(usuario.getNome());
        usuarioResponse.setDocumento(usuario.getDocumento());
        usuarioResponse.setEmail(usuario.getEmail());
        usuarioResponse.setTelefone(usuario.getTelefone());
        usuarioResponse.setCelular(usuario.getCelular());
        usuarioResponse.setDataCadastro(usuario.getDataCadastro());
        usuarioResponse.setClienteStatus(usuario.getClienteStatus());
        usuarioResponse.setTipoDocumento(usuario.getTipoDocumento());
        usuarioResponse.setEndereco(endereco);

        return usuarioResponse;
    }

    public Usuario toEntity(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.getNome());
        usuario.setDocumento(usuarioRequest.getDocumento());
        usuario.setSenha(usuarioRequest.getSenha());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setTelefone(usuarioRequest.getTelefone());
        usuario.setCelular(usuarioRequest.getCelular());
        usuario.setTipoDocumento(usuarioRequest.getTipoDocumento());

        return usuario;
    }

    public Usuario toEntityUpdate(UsuarioUpdateRequest usuarioUpdateRequestRequest, Usuario usuarioEntity) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioEntity.getId());
        usuario.setNome(usuarioUpdateRequestRequest.getNome());
        usuario.setDocumento(usuarioUpdateRequestRequest.getDocumento());
        usuario.setEmail(usuarioUpdateRequestRequest.getEmail());
        usuario.setTelefone(usuarioUpdateRequestRequest.getTelefone());
        usuario.setCelular(usuarioUpdateRequestRequest.getCelular());
        usuario.setClienteStatus(usuarioUpdateRequestRequest.getClienteStatus());
        usuario.setTipoDocumento(usuarioUpdateRequestRequest.getTipoDocumento());
        usuario.setDataCadastro(usuarioEntity.getDataCadastro());
        usuario.setSenha(usuarioEntity.getSenha());
        return usuario;
    }
}

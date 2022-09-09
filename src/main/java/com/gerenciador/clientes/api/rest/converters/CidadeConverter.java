package com.gerenciador.clientes.api.rest.converters;

import com.gerenciador.clientes.domain.entities.Cidade;
import com.gerenciador.clientes.domain.entities.Uf;
import com.gerenciador.clientes.api.rest.models.Cidade.CidadeRequest;
import com.gerenciador.clientes.api.rest.models.Cidade.CidadeResponse;
import com.gerenciador.clientes.api.rest.models.Cidade.CidadeUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class CidadeConverter {

    public CidadeResponse toModel(Cidade cidade, Uf uf){
        CidadeResponse cidadeResponse = new CidadeResponse();
        cidadeResponse.setId(cidade.getId());
        cidadeResponse.setNome(cidade.getNome());
        cidadeResponse.setUf(uf);

        return cidadeResponse;
    }

    public Cidade toEntity(CidadeRequest cidadeRequest, Uf uf){
        Cidade cidade = new Cidade();
        cidade.setNome(cidadeRequest.getNome());
        cidade.setUf(uf);
        return cidade;
    }

    public Cidade toEntityUpdate(CidadeUpdateRequest cidadeUpdateRequest, Cidade cidadeEntity , Uf uf){
        Cidade cidade = new Cidade();
        cidade.setId(cidadeEntity.getId());
        cidade.setNome(cidadeUpdateRequest.getNome());
        cidade.setUf(uf);
        return cidade;
    }
}

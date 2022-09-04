package com.gerenciador.clientes.converters;

import com.gerenciador.clientes.entities.Cidade;
import com.gerenciador.clientes.entities.Uf;
import com.gerenciador.clientes.models.Cidade.CidadeRequest;
import com.gerenciador.clientes.models.Cidade.CidadeResponse;
import com.gerenciador.clientes.models.Cidade.CidadeUpdateRequest;
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

package com.gerenciador.clientes.converters;

import com.gerenciador.clientes.entities.Uf;
import com.gerenciador.clientes.models.Uf.UfResponse;
import org.springframework.stereotype.Component;

@Component
public class UfConverter {//Classe para converter e não expor a entidade na requisição

    public UfResponse toModel(Uf uf){
        UfResponse ufResponse = new UfResponse();
        ufResponse.setId(uf.getId());
        ufResponse.setNome(uf.getNome());

        return ufResponse;
    }

}

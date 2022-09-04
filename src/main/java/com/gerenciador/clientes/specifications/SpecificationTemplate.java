package com.gerenciador.clientes.specifications;

import com.gerenciador.clientes.entities.Usuario;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationTemplate {
    //Anotação que combina os filtros
    @And({
            @Spec(path = "clienteStatus", spec = Equal.class)
    })

    public interface UsuarioSpec extends Specification<Usuario>{}
}

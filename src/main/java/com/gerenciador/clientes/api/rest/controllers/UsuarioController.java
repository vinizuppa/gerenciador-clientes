package com.gerenciador.clientes.api.rest.controllers;

import com.gerenciador.clientes.api.rest.converters.UsuarioConverter;
import com.gerenciador.clientes.domain.entities.Endereco;
import com.gerenciador.clientes.domain.entities.Usuario;
import com.gerenciador.clientes.api.rest.models.Usuario.UsuarioRequest;
import com.gerenciador.clientes.api.rest.models.Usuario.UsuarioResponse;
import com.gerenciador.clientes.api.rest.models.Usuario.UsuarioUpdateRequest;
import com.gerenciador.clientes.domain.enums.ExceptionMessagesEnum;
import com.gerenciador.clientes.domain.services.CidadeService;
import com.gerenciador.clientes.domain.services.EnderecoService;
import com.gerenciador.clientes.domain.services.UsuarioService;
import com.gerenciador.clientes.api.rest.specifications.SpecificationTemplate;
import com.gerenciador.clientes.infra.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
//Indica que o End-Point pode ser acessado de qualquer lugar. Soluciona problema de CORS.
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CidadeService cidadeService;

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    UsuarioConverter usuarioConverter;

    @PostMapping("/registro")
    public ResponseEntity<Object> insert(@RequestBody @Validated UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioConverter.toEntity(usuarioRequest);//Converte usuarioRequest em entidade Usuario
        usuarioService.save(usuario, usuarioRequest);

        Endereco endereco = enderecoService.findByUsuario(usuario).get();

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioConverter.toModel(usuario, endereco));
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "usuarioId") Integer usuarioId,
                                                      @RequestBody @Validated UsuarioUpdateRequest usuarioUpdateRequest){
        Usuario usuarioEntity = usuarioService.findById(usuarioId).orElseThrow(() -> new NotFoundException(ExceptionMessagesEnum.USUARIO_NOT_FOUND.name()));
        Usuario usuario = usuarioConverter.toEntityUpdate(usuarioUpdateRequest, usuarioEntity);
        usuarioService.updateUsuario(usuario, usuarioUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Usuario>> getAllUsuarios(SpecificationTemplate.UsuarioSpec spec,
                                                        @PageableDefault(page = 0, size = 20, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Usuario> usuarioPage = usuarioService.findAll(spec, pageable);
        if (!usuarioPage.isEmpty()) {//Verifica se trouxe algum usuario na busca
            for (Usuario usuario : usuarioPage.toList()) {//Acessando cada um dos elementos da lista
                usuario.add(linkTo(methodOn(UsuarioController.class).findById(usuario.getId())).withSelfRel());//Construindo Link do HATEOAS
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioPage);
    }

    @GetMapping("/id/{usuarioId}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable(value = "usuarioId") Integer usuarioId) {
        Usuario usuario = usuarioService.findById(usuarioId).orElseThrow(() -> new NotFoundException(ExceptionMessagesEnum.USUARIO_NOT_FOUND.getMessage()));

        Endereco endereco = enderecoService.findByUsuario(usuario).get();

        return ResponseEntity.status(HttpStatus.OK).body(usuarioConverter.toModel(usuario, endereco));
    }

    @GetMapping("/documento/{documento}")
    public ResponseEntity<UsuarioResponse> findByDocumento(@PathVariable(value = "documento") String documento) {
        Usuario usuario = usuarioService.findByDocumento(documento).orElseThrow(() -> new NotFoundException(ExceptionMessagesEnum.USUARIO_NOT_FOUND.getMessage()));

        Endereco endereco = enderecoService.findByUsuario(usuario).get();

        return ResponseEntity.status(HttpStatus.OK).body(usuarioConverter.toModel(usuario, endereco));
    }
}

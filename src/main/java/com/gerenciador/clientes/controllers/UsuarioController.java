package com.gerenciador.clientes.controllers;

import com.gerenciador.clientes.converters.UsuarioConverter;
import com.gerenciador.clientes.entities.Endereco;
import com.gerenciador.clientes.entities.Usuario;
import com.gerenciador.clientes.models.Usuario.UsuarioRequest;
import com.gerenciador.clientes.models.Usuario.UsuarioResponse;
import com.gerenciador.clientes.models.Usuario.UsuarioUpdateRequest;
import com.gerenciador.clientes.services.CidadeService;
import com.gerenciador.clientes.services.EnderecoService;
import com.gerenciador.clientes.services.UsuarioService;
import com.gerenciador.clientes.specifications.SpecificationTemplate;
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

        Endereco endereco = enderecoService.findByUsuario(usuario).orElseThrow();

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioConverter.toModel(usuario, endereco));
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "usuarioId") Integer usuarioId,
                                                      @RequestBody @Validated UsuarioUpdateRequest usuarioUpdateRequest){
        Usuario usuarioEntity = usuarioService.findById(usuarioId).orElseThrow();
        Usuario usuario = usuarioConverter.toEntityUpdate(usuarioUpdateRequest, usuarioEntity);
        usuarioService.updateUsuario(usuario, usuarioUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario alterado com sucesso.");
    }

//    @PutMapping("/{usuarioId}/status")
//    public ResponseEntity<Object> updateUsuarioStatus(@PathVariable(value = "usuarioId") Integer usuarioId,
//                                                      @RequestBody @Validated UsuarioStatusUpdateRequest usuarioStatusUpdateRequest){
//        Usuario usuario = usuarioService.findById(usuarioId).orElseThrow();
//        usuario.setClienteStatus(usuarioStatusUpdateRequest.getClienteStatus());
//        usuarioService.updateStatus(usuario);
//        return ResponseEntity.status(HttpStatus.OK).body("Usuario alterado com sucesso.");
//    }


    @GetMapping
    public ResponseEntity<Page<Usuario>> getAllUsuarioss(SpecificationTemplate.UsuarioSpec spec,
                                                        @PageableDefault(page = 0, size = 20, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Usuario> usuarioPage = usuarioService.findAll(spec, pageable);
        if (!usuarioPage.isEmpty()) {//Verifica se trouxe algum usuario na busca
            for (Usuario usuario : usuarioPage.toList()) {//Acessando cada um dos elementos da lista
                usuario.add(linkTo(methodOn(UsuarioController.class).findById(usuario.getId())).withSelfRel());//Construindo Link do HATEOAS
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioPage);
    }

//    @GetMapping
//    public ResponseEntity<Page<UsuarioResponse>> getAllUsuarios(SpecificationTemplate.UsuarioSpec spec,
//                                                        @PageableDefault(page = 0, size = 20, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
//        Page<Usuario> usuarioPage = usuarioService.findAll(spec, pageable);
//        if (!usuarioPage.isEmpty()) {//Verifica se trouxe algum usuario na busca
//            for (Usuario usuario : usuarioPage.toList()) {//Acessando cada um dos elementos da lista
//                usuario.add(linkTo(methodOn(UsuarioController.class).findById(usuario.getId())).withSelfRel());//Construindo Link do HATEOAS
//            }
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(usuarioConverter.toPage(usuarioPage));
//    }

    @GetMapping("/id/{usuarioId}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable(value = "usuarioId") Integer usuarioId) {
        Usuario usuario = usuarioService.findById(usuarioId).orElseThrow();

        Endereco endereco = enderecoService.findByUsuario(usuario).orElseThrow();

        return ResponseEntity.status(HttpStatus.OK).body(usuarioConverter.toModel(usuario, endereco));
    }

    @GetMapping("/documento/{documento}")
    public ResponseEntity<UsuarioResponse> findByDocumento(@PathVariable(value = "documento") String documento) {
        Usuario usuario = usuarioService.findByDocumento(documento).orElseThrow();

        Endereco endereco = enderecoService.findByUsuario(usuario).orElseThrow();

        return ResponseEntity.status(HttpStatus.OK).body(usuarioConverter.toModel(usuario, endereco));
    }
}

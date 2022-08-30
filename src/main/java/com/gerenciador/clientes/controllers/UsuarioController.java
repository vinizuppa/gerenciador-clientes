package com.gerenciador.clientes.controllers;

import com.gerenciador.clientes.dtos.UsuarioDto;
import com.gerenciador.clientes.entities.Endereco;
import com.gerenciador.clientes.entities.Usuario;
import com.gerenciador.clientes.enums.ClienteStatus;
import com.gerenciador.clientes.services.CidadeService;
import com.gerenciador.clientes.services.EnderecoService;
import com.gerenciador.clientes.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600) //Indica que o End-Point pode ser acessado de qualquer lugar. Soluciona problema de CORS.
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CidadeService cidadeService;

    @Autowired
    EnderecoService enderecoService;

    @PostMapping("/registro")
    public ResponseEntity<Object> insert(@RequestBody UsuarioDto usuarioDto){
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);
        usuario.setDataCadastro(LocalDate.now(ZoneId.of("UTC")));
        usuario.setStatus(ClienteStatus.ATIVO);
        usuarioService.save(usuario);

        var endereco = new Endereco();
        endereco.setEndereco(usuarioDto.getEndereco());
        endereco.setNumero(usuarioDto.getNumero());
        endereco.setBairro(usuarioDto.getBairro());
        endereco.setCep(usuarioDto.getCep());
        endereco.setCidade(cidadeService.findById(usuarioDto.getCidadeId()).get());
        endereco.setUsuario(usuarioService.findById(usuario.getId()).get());
        enderecoService.save(endereco);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Object> findById(@PathVariable (value = "usuarioId") Integer usuarioId){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(usuarioId));
    }
}

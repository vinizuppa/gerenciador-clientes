package com.gerenciador.clientes.controllers;

import com.gerenciador.clientes.dtos.CidadeDto;
import com.gerenciador.clientes.entities.Cidade;
import com.gerenciador.clientes.entities.Uf;
import com.gerenciador.clientes.services.CidadeService;
import com.gerenciador.clientes.services.UfService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600) //Indica que o End-Point pode ser acessado de qualquer lugar. Soluciona problema de CORS.
@RequestMapping("/cidades")
public class CidadeController {
    @Autowired
    CidadeService cidadeService;

    @Autowired
    UfService ufService;

    @GetMapping
    public ResponseEntity<Page<Cidade>> getAllCidades(@PageableDefault(page = 0, size = 30, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Cidade> cidadePage = cidadeService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(cidadePage);
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Object> findById(@PathVariable(value = "cidadeId") Integer cidadeId){
        Optional<Cidade> cidadeOptional = cidadeService.findById(cidadeId);
        if (!cidadeOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cidadeOptional.get());
    }

    @PostMapping("/registro")
    public ResponseEntity<Object> insert(@RequestBody @Validated CidadeDto cidadeDto){

        Uf uf = ufService.findById(cidadeDto.getUfId())
                .orElseThrow(() -> new RuntimeException("Erro: Uf não encontrada"));//Verifica se a Uf existe, caso não, lança uma exceção.

        Optional<Cidade> cidadeOptional = cidadeService.findByNome(cidadeDto.getNome());
        if (cidadeOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Nome de cidade já cadastrado");
        }

        var cidade = new Cidade();
        BeanUtils.copyProperties(cidadeDto, cidade);
        cidade.setUf(ufService.findById(cidadeDto.getUfId()).get());
        cidadeService.save(cidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
    }
}

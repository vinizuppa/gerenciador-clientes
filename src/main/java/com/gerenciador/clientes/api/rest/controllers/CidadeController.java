package com.gerenciador.clientes.api.rest.controllers;

import com.gerenciador.clientes.api.rest.converters.CidadeConverter;
import com.gerenciador.clientes.api.rest.models.Cidade.CidadeRequest;
import com.gerenciador.clientes.api.rest.models.Cidade.CidadeResponse;
import com.gerenciador.clientes.api.rest.models.Cidade.CidadeUpdateRequest;
import com.gerenciador.clientes.domain.entities.Cidade;
import com.gerenciador.clientes.domain.entities.Uf;
import com.gerenciador.clientes.domain.enums.ExceptionMessagesEnum;
import com.gerenciador.clientes.domain.services.CidadeService;
import com.gerenciador.clientes.domain.services.UfService;
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

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600) //Indica que o End-Point pode ser acessado de qualquer lugar. Soluciona problema de CORS.
@RequestMapping("/cidades")
public class CidadeController {
    @Autowired
    CidadeService cidadeService;

    @Autowired
    UfService ufService;

    @Autowired
    CidadeConverter cidadeConverter;

    @GetMapping
    public ResponseEntity<List<Cidade>> getAllCidades(){
        List<Cidade> cidade = cidadeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(cidade);
    }

    @GetMapping("/id/{cidadeId}")
    public ResponseEntity<CidadeResponse> findById(@PathVariable(value = "cidadeId") Integer cidadeId){
        Cidade cidade = cidadeService.findById(cidadeId).orElseThrow(() -> new NotFoundException(ExceptionMessagesEnum.CIDADE_NOT_FOUND.getMessage()));

        Uf uf = ufService.findById(cidade.getUf().getId()).orElseThrow(() -> new NotFoundException(ExceptionMessagesEnum.UF_NOT_FOUND.getMessage()));

        return ResponseEntity.status(HttpStatus.OK).body(cidadeConverter.toModel(cidade, uf));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Object> findByNome(@PathVariable(value = "nome") String nome){
        Cidade cidade = cidadeService.findByNome(nome).orElseThrow(() -> new NotFoundException(ExceptionMessagesEnum.CIDADE_NOT_FOUND.getMessage()));

        return ResponseEntity.status(HttpStatus.OK).body(cidade);
    }



    @PostMapping("/registro")
    public ResponseEntity<Object> insert(@RequestBody @Validated CidadeRequest cidadeRequest){

        Uf uf = ufService.findById(cidadeRequest.getUfId())
                .orElseThrow(() -> new NotFoundException(ExceptionMessagesEnum.UF_NOT_FOUND.getMessage()));//Verifica se a Uf existe, caso não, lança uma exceção.

        Cidade cidade = cidadeConverter.toEntity(cidadeRequest, uf);
        cidadeService.save(cidade);

        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeConverter.toModel(cidade, uf));
    }

    @PutMapping("{cidadeId}")
    public ResponseEntity<Object> updateCidade(@PathVariable(value = "cidadeId") Integer cidadeId,
                                               @RequestBody @Validated CidadeUpdateRequest cidadeUpdateRequest){
        Cidade cidadeEntity = cidadeService.findById(cidadeId).orElseThrow(() -> new NotFoundException(ExceptionMessagesEnum.CIDADE_NOT_FOUND.getMessage()));
        Uf uf = ufService.findById(cidadeUpdateRequest.getUfId()).orElseThrow(() -> new NotFoundException(ExceptionMessagesEnum.UF_NOT_FOUND.getMessage()));

        Cidade cidade = cidadeConverter.toEntityUpdate(cidadeUpdateRequest, cidadeEntity, uf);
        cidadeService.updateCidade(cidade);
        return ResponseEntity.status(HttpStatus.OK).body(cidadeConverter.toModel(cidade, uf));
    }
}

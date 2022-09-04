package com.gerenciador.clientes.controllers;

import com.gerenciador.clientes.converters.CidadeConverter;
import com.gerenciador.clientes.models.Cidade.CidadeRequest;
import com.gerenciador.clientes.entities.Cidade;
import com.gerenciador.clientes.entities.Uf;
import com.gerenciador.clientes.models.Cidade.CidadeResponse;
import com.gerenciador.clientes.models.Cidade.CidadeUpdateRequest;
import com.gerenciador.clientes.services.CidadeService;
import com.gerenciador.clientes.services.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
    public ResponseEntity<Page<Cidade>> getAllCidades(@PageableDefault(page = 0, size = 30, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable){
        Page<Cidade> cidadePage = cidadeService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(cidadePage);
    }

    @GetMapping("/id/{cidadeId}")
    public ResponseEntity<CidadeResponse> findById(@PathVariable(value = "cidadeId") Integer cidadeId){
        Cidade cidade = cidadeService.findById(cidadeId).orElseThrow();

        Uf uf = ufService.findById(cidade.getUf().getId()).orElseThrow();

        return ResponseEntity.status(HttpStatus.OK).body(cidadeConverter.toModel(cidade, uf));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Object> findByNome(@PathVariable(value = "nome") String nome){
        Optional<Cidade> cidadeOptional = cidadeService.findByNome(nome);
        if (!cidadeOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cidadeOptional.get());
    }



    @PostMapping("/registro")
    public ResponseEntity<Object> insert(@RequestBody @Validated CidadeRequest cidadeRequest){

        Uf uf = ufService.findById(cidadeRequest.getUfId())
                .orElseThrow(() -> new RuntimeException("Erro: Uf não encontrada"));//Verifica se a Uf existe, caso não, lança uma exceção.

        Cidade cidade = cidadeConverter.toEntity(cidadeRequest, uf);
        cidadeService.save(cidade);

        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeConverter.toModel(cidade, uf));
    }

    @PutMapping("{cidadeId}")
    public ResponseEntity<Object> updateCidade(@PathVariable(value = "cidadeId") Integer cidadeId,
                                               @RequestBody @Validated CidadeUpdateRequest cidadeUpdateRequest){
        Cidade cidadeEntity = cidadeService.findById(cidadeId).orElseThrow();
        Uf uf = ufService.findById(cidadeEntity.getUf().getId()).orElseThrow();

        Cidade cidade = cidadeConverter.toEntityUpdate(cidadeUpdateRequest, cidadeEntity, uf);
        cidadeService.updateCidade(cidade);
        return ResponseEntity.status(HttpStatus.OK).body("Cidade alterada com sucesso.");
    }
}

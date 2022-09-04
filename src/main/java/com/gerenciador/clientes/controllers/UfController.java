package com.gerenciador.clientes.controllers;

import com.gerenciador.clientes.converters.UfConverter;
import com.gerenciador.clientes.entities.Uf;
import com.gerenciador.clientes.models.Uf.UfResponse;
import com.gerenciador.clientes.services.UfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600) //Indica que o End-Point pode ser acessado de qualquer lugar. Soluciona problema de CORS.
@RequestMapping("/ufs")
public class UfController {
    @Autowired
    UfService ufService;

    @Autowired
    UfConverter ufConverter;

    @GetMapping
    public ResponseEntity<Page<Uf>> getAllUfs(@PageableDefault(page = 0, size = 30, sort = "nome", direction = Sort.Direction.ASC)Pageable pageable){
        Page<Uf> ufPage = ufService.findAll(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(ufPage);
    }

    @GetMapping("/{ufId}")
    public ResponseEntity<UfResponse> findById(@PathVariable(value = "ufId") Integer ufId){
        Uf uf = ufService.findById(ufId).orElseThrow();

        return ResponseEntity.status(HttpStatus.OK).body(ufConverter.toModel(uf));
    }
}

package com.gerenciador.clientes.api.rest.controllers;

import com.gerenciador.clientes.api.rest.converters.UfConverter;
import com.gerenciador.clientes.domain.entities.Uf;
import com.gerenciador.clientes.api.rest.models.Uf.UfResponse;
import com.gerenciador.clientes.domain.enums.ExceptionMessagesEnum;
import com.gerenciador.clientes.domain.services.UfService;
import com.gerenciador.clientes.infra.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600) //Indica que o End-Point pode ser acessado de qualquer lugar. Soluciona problema de CORS.
@RequestMapping("/ufs")
public class UfController {
    @Autowired
    UfService ufService;

    @Autowired
    UfConverter ufConverter;

    @GetMapping
    public ResponseEntity<List<Uf>>getAllUfs(){
        List<Uf> ufList = ufService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(ufList);
    }

    @GetMapping("/{ufId}")
    public ResponseEntity<UfResponse> findById(@PathVariable(value = "ufId") Integer ufId){
        Uf uf = ufService.findById(ufId).orElseThrow(() -> new NotFoundException(ExceptionMessagesEnum.UF_NOT_FOUND.getMessage()));

        return ResponseEntity.status(HttpStatus.OK).body(ufConverter.toModel(uf));
    }
}

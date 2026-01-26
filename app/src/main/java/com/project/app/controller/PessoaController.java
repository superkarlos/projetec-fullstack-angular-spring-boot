package com.project.app.controller;


import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.app.event.EventoCriado;
import com.project.app.exption.types.EntityNotFoundExceptionHandler;
import com.project.app.model.Pessoa;
import com.project.app.services.PessoaServices;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaServices pessoaServices;
    private final ApplicationEventPublisher publisher;

    public PessoaController(PessoaServices pessoaServices, ApplicationEventPublisher publisher) {
        this.pessoaServices = pessoaServices;
        this.publisher = publisher;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listPersons() {
        return ResponseEntity.ok(pessoaServices.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPessoa(@PathVariable Long id) {
        return pessoaServices.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Pessoa não encontrada"));
    }

    @PostMapping()
    public ResponseEntity<Pessoa> save(@RequestBody @Valid Pessoa pessoa, HttpServletResponse response) {

        Pessoa data = pessoaServices.save(pessoa);
        publisher.publishEvent(new EventoCriado(this, response, data.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws EntityNotFoundExceptionHandler  {
        pessoaServices.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada"); // 404
    }

}

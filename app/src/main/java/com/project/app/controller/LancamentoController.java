package com.project.app.controller;

import java.net.URI;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.app.event.EventoCriado;
import com.project.app.exption.types.BusinessException;
import com.project.app.model.Lancamento;
import com.project.app.repository.filter.LancamentoFilter;
import com.project.app.services.LancamentoService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    private final LancamentoService lancamentoService;
    private final ApplicationEventPublisher publisher;

    public LancamentoController(LancamentoService lancamentoService, ApplicationEventPublisher publisher) {
        this.lancamentoService = lancamentoService;
        this.publisher = publisher;
    }

    @GetMapping("/buscar-page")
    public Page<Lancamento> pesquisarPage(LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoService.filtrar(lancamentoFilter, pageable);
    }

    @GetMapping("/buscar-todos")
    public ResponseEntity<List<Lancamento>> buscarTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(lancamentoService.findAll());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Lancamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(lancamentoService.findById(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Lancamento> save(@RequestBody @Valid Lancamento lancamento, BindingResult bg) {
         
        if (bg.hasErrors()) {
            throw new BusinessException("Campos invalidso");
        }
        Lancamento data = lancamentoService.save(lancamento);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri() 
                .replacePath("/project/lancamentos/buscar/{id}")
                .buildAndExpand(data.getCodigo())
                .toUri();

        return ResponseEntity.created(location).body(data);
    }

}

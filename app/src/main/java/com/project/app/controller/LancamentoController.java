package com.project.app.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.app.model.Lancamento;
import com.project.app.repository.filter.LancamentoFilter;
import com.project.app.services.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    private final LancamentoService lancamentoService;

    public LancamentoController(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
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

}

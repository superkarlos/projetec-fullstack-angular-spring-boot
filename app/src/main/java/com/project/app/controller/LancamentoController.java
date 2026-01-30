package com.project.app.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping("/buscar")
    public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter) {
        return lancamentoService.pesquisar(lancamentoFilter);
    }

    //  método novo (Specification + Pageable)
    @GetMapping("/buscar-page")
    public Page<Lancamento> pesquisarPage(LancamentoFilter lancamentoFilter,Pageable pageable) {

        return lancamentoService.filtrar(lancamentoFilter, pageable);
    }
}

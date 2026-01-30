package com.project.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.project.app.model.Lancamento;
import com.project.app.repository.LancamentoRepository;
import com.project.app.repository.filter.LancamentoFilter;
import com.project.app.repository.imp.GenericSpecification;

@Service
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;

    public LancamentoService(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable) {

        GenericSpecification<Lancamento> specBuilder =new GenericSpecification<>();
        Specification<Lancamento> spec =specBuilder.build(filter);
        return lancamentoRepository.findAll(spec, pageable);
    }

    public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter) {
        return lancamentoRepository.filtrar(lancamentoFilter);
    }
}

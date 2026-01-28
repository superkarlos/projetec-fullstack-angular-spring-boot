package com.project.app.controller;

import java.util.List;
import java.util.concurrent.Flow.Publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.app.model.Lancamento;
import com.project.app.repository.LancamentoRepository;
import com.project.app.repository.filter.LancamentoFilter;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired 
    private Publisher publisher;
    
    @Autowired 
    private MessageSource messageSource;

    @Autowired
    private LancamentoRepository repository;

    @GetMapping()
    public List<Lancamento> findy(LancamentoFilter filter){
        return repository.findAll();
    }
    
}

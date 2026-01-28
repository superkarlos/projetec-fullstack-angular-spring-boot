package com.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.app.model.Lancamento;
import com.project.app.repository.query.LancamentoRepositoryQuery;

public interface LancamentoRepository  extends JpaRepository<Lancamento,Long>,LancamentoRepositoryQuery{
    
}

package com.project.app.repository.query;

import java.util.List;
import com.project.app.model.Lancamento;
import com.project.app.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
    public List<Lancamento> filtrar ( LancamentoFilter filter);
}

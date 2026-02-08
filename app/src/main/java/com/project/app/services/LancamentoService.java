package com.project.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.project.app.exption.types.EntityNotFoundException;
import com.project.app.exption.types.PessoaInexistenteOuIntiva;
import com.project.app.model.Lancamento;
import com.project.app.model.Pessoa;
import com.project.app.repository.LancamentoRepository;
import com.project.app.repository.PessoaRepository;
import com.project.app.repository.filter.LancamentoFilter;
import com.project.app.repository.imp.GenericSpecification;

@Service
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final PessoaRepository pessoaRepository;

    public LancamentoService(LancamentoRepository lancamentoRepository,PessoaRepository pessoaRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable) {
        GenericSpecification<Lancamento> specBuilder = new GenericSpecification<>();
        Specification<Lancamento> spec = specBuilder.build(filter);
        return lancamentoRepository.findAll(spec, pageable);
    }

    public List<Lancamento> findAll() {
        return lancamentoRepository.findAll();
    }

    public Lancamento findById(Long id) {
        Optional<Lancamento> data = lancamentoRepository.findById(id);
        if (data.isEmpty()) {
            throw new EntityNotFoundException("Lancamento não exites");
        }
        return data.get();

    }

    public Lancamento save(Lancamento data) {

        Optional<Pessoa> pessoa = pessoaRepository.findById(data.getPessoa().getCodigo());

        if(pessoa.isEmpty() || !pessoa.get().getAtivo()){
            throw new PessoaInexistenteOuIntiva("O Pessoa informada não exite ou está inativa!");
        }
        return lancamentoRepository.save(data);
    }

    public void dell(Long id){
      findById(id);
      lancamentoRepository.deleteById(id);
    }

}

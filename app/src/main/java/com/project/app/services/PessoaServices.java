package com.project.app.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.project.app.exption.types.EntityNotFoundExceptionHandler;
import com.project.app.model.Pessoa;
import com.project.app.repository.PessoaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaServices {

    private final PessoaRepository repository;

    public List<Pessoa> getAll() {
        return this.repository.findAll();
    }

    public Optional<Pessoa> findById(Long Id) {
        return this.repository.findById(Id);
    }

    public Pessoa save(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public boolean delete(Long id) throws EntityNotFoundExceptionHandler  {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundExceptionHandler (" dddddddd Não encontrada");
        }
        repository.deleteById(id);
        return true;
    }

}

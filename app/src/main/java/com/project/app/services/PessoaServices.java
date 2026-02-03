package com.project.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.project.app.exption.types.EntityNotFoundException;
import com.project.app.model.Endereco;
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

    public Pessoa findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
    }


    public Pessoa save(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    public boolean delete(Long id) throws EntityNotFoundException {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("pessoa não encontrada!");
        }
        repository.deleteById(id);
        return true;
    }

    public Pessoa atualizar(Pessoa pessoa, Long id) throws EntityNotFoundException {

        Pessoa pessoaBanco = findById(id);
       // copie de para iguinorando
        BeanUtils.copyProperties(pessoa, pessoaBanco,"codigo");
        return repository.save(pessoaBanco);
    }

    public Pessoa atualizarAtivo(Long id, Boolean ativo) throws EntityNotFoundException{
        Pessoa pessoa = findById(id);
        pessoa.setAtivo(ativo);
        return repository.save(pessoa);
    }

    private void atualizarEndereco(Pessoa antes, Pessoa depois) {

        Endereco endAntes = antes.getEndereco();
        Endereco endDepois = depois.getEndereco();

        if (endDepois.getLogradouro() != null) {
            endAntes.setLogradouro(endDepois.getLogradouro());
        }

        if (endDepois.getNumero() != null) {
            endAntes.setNumero(endDepois.getNumero());
        }

        if (endDepois.getComplemento() != null) {
            endAntes.setComplemento(endDepois.getComplemento());
        }

        if (endDepois.getBairro() != null) {
            endAntes.setBairro(endDepois.getBairro());
        }

        if (endDepois.getCep() != null) {
            endAntes.setCep(endDepois.getCep());
        }

        if (endDepois.getCidade() != null) {
            endAntes.setCidade(endDepois.getCidade());
        }

        if (endDepois.getEstado() != null) {
            endAntes.setEstado(endDepois.getEstado());
        }
    }

}

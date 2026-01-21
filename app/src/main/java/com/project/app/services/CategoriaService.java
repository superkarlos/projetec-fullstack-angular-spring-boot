package com.project.app.services;

import java.util.List;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.project.app.model.Categoria;
import com.project.app.repository.CategoriaRepository;

@Service
public class CategoriaService {
    
    private final CategoriaRepository repository;
   
    
    public CategoriaService(CategoriaRepository categoriaRepository){
        this.repository = categoriaRepository;
    }

    public List<Categoria> getall(){
      return repository.findAll();
    }

    public Categoria salvar(Categoria categoria){
       return repository.save(categoria);
    }

    public Optional<Categoria> findyById(Long id){
        return repository.findById(id);
    }
}

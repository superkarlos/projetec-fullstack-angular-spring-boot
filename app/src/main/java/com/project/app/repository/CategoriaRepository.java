package com.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.app.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> { }
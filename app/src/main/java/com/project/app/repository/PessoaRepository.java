package com.project.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.app.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {}

package com.project.app.repository.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.project.app.model.Lancamento;
import com.project.app.repository.filter.LancamentoFilter;
//import com.project.app.repository.query.LancamentoRepositoryQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

// @Repository
// public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

//     @PersistenceContext
//     private EntityManager manager;

//     @Override
//     public List<Lancamento> filtrar(LancamentoFilter filter) {

//         CriteriaBuilder cb = manager.getCriteriaBuilder();
//         CriteriaQuery<Lancamento> cq = cb.createQuery(Lancamento.class);
//         Root<Lancamento> root = cq.from(Lancamento.class);

//         List<Predicate> predicates = new ArrayList<>();

//         // Exemplo de filtro
//         if (filter.getDescricao() != null) {
//             predicates.add( 
//                 cb.like( cb.lower(root.get("descricao")), "%" + filter.getDescricao().toLowerCase() + "%" 
//             ) );
//         }

//         if (filter.getTipo() != null) {
//             predicates.add( 
//                 cb.like( cb.lower(root.get("tipo")), "%" + filter.getTipo().toLowerCase() + "%" 
//             ) );
//         }

       
//         cq.where(predicates.toArray(new Predicate[0]));

//         return manager.createQuery(cq).getResultList();
//     }


// }

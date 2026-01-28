package com.project.app.repository.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.project.app.model.Lancamento;
import com.project.app.repository.filter.LancamentoFilter;
import com.project.app.repository.query.LancamentoRepositoryQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class LancamentoRepositoyImp implements LancamentoRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Lancamento> filtrar(LancamentoFilter filter) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteriaQuery = builder.createQuery(Lancamento.class);
        TypedQuery<Lancamento> query = manager.createQuery(criteriaQuery);

        Root<Lancamento> root = criteriaQuery.from(Lancamento.class);
        //criar restriçoes 
        Predicate [] predicates = criarRestricoes(filter,builder,root);
        criteriaQuery.where(predicates);

       return query.getResultList();
    }

    private Predicate[] criarRestricoes(LancamentoFilter filter,CriteriaBuilder builder,Root<Lancamento> root){

        List<Predicate> predicates = new ArrayList();

        if (!StringUtils.isEmpty(filter.getDescricao())) {
            String fitroSql = "%" + filter.getDescricao().toLowerCase() + "%";
            predicates.add(builder.like(builder.lower(root.get("descricao")) ,fitroSql ));
        }
        if (filter.getDataVencimentoDe() != null) {
            
        }
        if (filter.getDataVencimentoAte() != null) {
            
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    };
    
}

package com.project.app.repository.imp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class GenericSpecification<T> {

    public Specification<T> build(Object filter) {

        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            for (Field field : filter.getClass().getDeclaredFields()) {
                field.setAccessible(true);

                try {
                    Object value = field.get(filter);
                    if (value == null) continue;

                    String fieldName = field.getName();

                    if (value instanceof String) {
                        predicates.add(
                            cb.like(
                                cb.lower(root.get(fieldName)),
                                "%" + value.toString().toLowerCase() + "%"
                            )
                        );
                    } else {
                        predicates.add(
                            cb.equal(root.get(fieldName), value)
                        );
                    }

                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Erro ao montar filtro", e);
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

package com.time7.bolaodacopa.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelecaoRepository extends CrudRepository<SelecaoModel, Long> {
    
}

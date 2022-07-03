package com.catering.repository;

import com.catering.model.Buffet;
import com.catering.model.Chef;
import com.catering.model.Piatto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {
    public List<Buffet> findByName(String name);

    public List<Buffet> findAllByPiattiContaining(Piatto piatto);

    public List<Buffet> findAllByChefContaining(Chef chef);

}

package com.catering.repository;

import com.catering.model.Buffet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {
    public List<Buffet> findByName(String name);
}

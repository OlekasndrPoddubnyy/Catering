package com.catering.repository;

import com.catering.model.Piatto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PiattoRepository extends CrudRepository<Piatto, Long> {
    public List<Piatto> findByName(String name);
}

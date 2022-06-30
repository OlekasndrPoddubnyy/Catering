package com.catering.repository;

import com.catering.model.Ingrediente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {
    public List<Ingrediente> findByName(String name);
}

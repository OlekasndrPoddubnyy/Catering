package com.catering.repository;

import com.catering.model.Buffet;
import com.catering.model.Chef;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChefRepository extends CrudRepository<Chef, Long> {
    public List<Chef> findByName(String name);

    public List<Chef> findByBuffetsContaining(Buffet buffet);
}

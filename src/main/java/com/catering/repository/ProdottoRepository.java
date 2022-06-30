package com.catering.repository;

import java.util.List;

import com.catering.model.Prodotto;
import org.springframework.data.repository.CrudRepository;

public interface ProdottoRepository extends CrudRepository<Prodotto, Long> {

    public List<Prodotto> findByNome(String nome);
}
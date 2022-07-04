package com.catering.service;

import com.catering.model.Ingrediente;
import com.catering.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Transactional
    public Ingrediente inserisci(Ingrediente ingrediente) { return ingredienteRepository.save(ingrediente);}

    @Transactional
    public List<Ingrediente> tutti() { return (List<Ingrediente>) ingredienteRepository.findAll(); }

    @Transactional
    public Ingrediente ingredientePerId(Long id) {
        Optional<Ingrediente> optional = ingredienteRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        else
            return null;
    }

    @Transactional
    public boolean alreadyExists(Ingrediente ingrediente) {
        List<Ingrediente> ingredients = ingredienteRepository.findByName(ingrediente.getName());
        if (ingredients.size() > 0)
            return true;
        else
            return false;
    }

    @Transactional
    public void deleteIngrediente(Long id) {
        this.ingredienteRepository.deleteById(id);
    }
}

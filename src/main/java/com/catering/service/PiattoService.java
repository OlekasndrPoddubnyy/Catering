package com.catering.service;

import com.catering.model.Buffet;
import com.catering.model.Piatto;
import com.catering.repository.PiattoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PiattoService {

    @Autowired
    private PiattoRepository piattoRepository;

    @Transactional
    public Piatto inserisci(Piatto piatto) { return piattoRepository.save(piatto);}

    @Transactional
    public List<Piatto> inserisciTutti(List<Piatto> piatti) { return (List<Piatto>) piattoRepository.saveAll(piatti);}

    @Transactional
    public List<Piatto> tutti() { return (List<Piatto>) piattoRepository.findAll(); }

    @Transactional
    public Piatto piattoPerId(Long id) {
        Optional<Piatto> optional = piattoRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        else
            return null;
    }

    @Transactional
    public boolean alreadyExists(Piatto piatto) {
        List<Piatto> piatti = piattoRepository.findByName(piatto.getName());
        if (piatti.size() > 0)
            return true;
        else
            return false;
    }

    @Transactional
    public void deletePiatto(Long id) {
        this.piattoRepository.deleteById(id);
    }

    @Transactional
    public void addIngredienteforPiatto(Long idP, Long idI){
        this.piattoRepository.addIngredienteforPiatto(idP,idI);
    }

    @Transactional
    public void deleteIngredienteforAll(Long idI){
        this.piattoRepository.deleteIngredienteforAll(idI);
    }

    @Transactional
    public void deleteIngredienteforPiatto(Long idP, Long idI){
        this.piattoRepository.deleteIngredienteforPiatto(idP,idI);
    }

    @Transactional
    public void update(Piatto piattoMod){
        this.piattoRepository.update(piattoMod.getId(),piattoMod.getName(),piattoMod.getDescription());
    }

}

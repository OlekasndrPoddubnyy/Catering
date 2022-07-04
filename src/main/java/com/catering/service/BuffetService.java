package com.catering.service;

import com.catering.model.Buffet;
import com.catering.repository.BuffetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BuffetService {

    @Autowired
    private BuffetRepository buffetRepository;

    @Transactional
    public Buffet inserisci(Buffet buffet){ return buffetRepository.save(buffet);}


    @Transactional
    public List<Buffet> inserisciTutti(List<Buffet> buffets){ return (List<Buffet>) buffetRepository.saveAll(buffets);}

    @Transactional
    public List<Buffet> tutti() { return (List<Buffet>) buffetRepository.findAll(); }

    @Transactional
    public Buffet buffetPerId(Long id) {
        Optional<Buffet> optional = buffetRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        else
            return null;
    }

    @Transactional
    public boolean alreadyExists(Buffet buffet) {
        Optional<Buffet> optional = buffetRepository.findById(buffet.getId());
        if (optional.isPresent())
            return true;
        else
            return false;
    }

    @Transactional
    public void deleteBuffet(Long id) {
        this.buffetRepository.deleteById(id);
    }

    @Transactional
    public void addPiattoforBuffet(Long idB, Long idP){
        this.buffetRepository.addPiattoforBuffet(idB,idP);
    }

    @Transactional
    public void deletePiattoforAll(Long idP){
        this.buffetRepository.deletePiattoforAll(idP);
    }

    @Transactional
    public void deletePiattoforBuffet(Long idB, Long idP){
        this.buffetRepository.deletePiattoforBuffet(idB,idP);
    }

    @Transactional
    public void setChefBuffet(Long idC, Long idB){
        this.buffetRepository.setChefBuffet(idC, idB);
    }

    @Transactional
    public void setAllChefBuffet(Long idC, Long idC2){
        this.buffetRepository.setAllChefBuffet(idC, idC2);
    }

    @Transactional
    public void delAllChefBuffet(Long idC){
        this.buffetRepository.delAllChefBuffet(idC);
    }

    @Transactional
    public void delChefBuffet(Long idB){
        this.buffetRepository.delChefBuffet(idB);
    }

}

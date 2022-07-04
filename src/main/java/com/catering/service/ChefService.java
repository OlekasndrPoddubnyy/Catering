package com.catering.service;

import com.catering.model.Chef;
import com.catering.repository.ChefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChefService {

    @Autowired
    private ChefRepository chefRepository;

    @Transactional
    public Chef inserisci(Chef chef) { return chefRepository.save(chef);}

    @Transactional
    public List<Chef> inserisciTutti(List<Chef> chefs) { return (List<Chef>) chefRepository.saveAll(chefs);}

    @Transactional
    public List<Chef> tutti() { return (List<Chef>) chefRepository.findAll(); }

    @Transactional
    public Chef chefPerId(Long id) {
        Optional<Chef> optional = chefRepository.findById(id);
        if (optional.isPresent())
            return optional.get();
        else
            return null;
    }

    @Transactional
    public boolean alreadyExists(Chef chef) {
        Optional<Chef> optional = chefRepository.findById(chef.getId());
        if (optional.isPresent())
            return true;
        else
            return false;
    }

    @Transactional
    public void deleteChef(Long id) {
       this.chefRepository.deleteById(id);
    }

    @Transactional
    public void addBuffetforChef(Long idC, Long idB){
    this.chefRepository.addBuffetforChef(idC,idB);
    }

    @Transactional
    public void deleteBuffetforAll(Long idB){
        this.chefRepository.deleteBuffetforAll(idB);
    }

    @Transactional
    public void deleteBuffetforChef(Long idC){
        this.chefRepository.deleteBuffetforChef(idC);
    }

    @Transactional
    public void deleteBuffetforChef2(Long idC, Long idB){
        this.chefRepository.deleteBuffetforChef2(idC, idB);
    }


}

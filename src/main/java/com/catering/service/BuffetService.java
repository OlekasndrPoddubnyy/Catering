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
        List<Buffet> buffets = buffetRepository.findByName(buffet.getName());
        if (buffets.size() > 0)
            return true;
        else
            return false;
    }

    @Transactional
    public void deleteBuffet(Buffet buffet) {
        buffetRepository.delete(buffet);
    }
}

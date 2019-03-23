package com.gift.registry.services.impl;

import com.gift.registry.domain.Occasion;
import com.gift.registry.repository.OccasionRepository;
import com.gift.registry.services.OccasionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OccasionServiceImpl implements OccasionService{
    @Autowired
    private OccasionRepository occasionRepository;
    
    @Override
    public Occasion find(Long id) {
        return occasionRepository.findOne(id);                
    }

    @Override
    public Occasion persist(Occasion entity) {
        return occasionRepository.save(entity);
    }

    @Override
    public Occasion merge(Occasion entity) {
        if (entity.getId()!=null) {
            return occasionRepository.save(entity);
        }
        return null;
    }

    @Override
    public void remove(Occasion entity) {
        occasionRepository.delete(entity);
    }

    @Override
    public List<Occasion> findAll() {
       return occasionRepository.findAll();
    }    
}

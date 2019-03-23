package com.gift.registry.services.impl;

import com.gift.registry.domain.ProductOccasion;
import com.gift.registry.repository.ProductOccasionRepository;
import com.gift.registry.services.ProductOccasionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductOccasionServiceImpl implements ProductOccasionService{
    @Autowired
    private ProductOccasionRepository productOccasionRepository;

    @Override
    public ProductOccasion find(Long id) {
        return productOccasionRepository.findOne(id);
    }

    @Override
    public ProductOccasion persist(ProductOccasion entity) {
        return productOccasionRepository.save(entity);
    }

    @Override
    public ProductOccasion merge(ProductOccasion entity) {
        if (entity.getId()!=null) {
            return productOccasionRepository.save(entity);
        }
        return null;
    }

    @Override
    public void remove(ProductOccasion entity) {
        productOccasionRepository.delete(entity);
    }

    @Override
    public List<ProductOccasion> findAll() {
        return productOccasionRepository.findAll();
    }
    
}
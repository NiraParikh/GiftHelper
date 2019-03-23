package com.gift.registry.services.impl;

import com.gift.registry.domain.Delivery;
import com.gift.registry.repository.DeliveryRepository;
import com.gift.registry.services.DeliveryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;
    
    @Override
    public Delivery find(Long id) {
        return deliveryRepository.findOne(id);
    }

    @Override
    public Delivery persist(Delivery entity) {
        return deliveryRepository.save(entity);
    }

    @Override
    public Delivery merge(Delivery entity) {
        if (entity.getId()!=null) {
            return deliveryRepository.save(entity);
        }
        return null;
    }

    @Override
    public void remove(Delivery entity) {
        deliveryRepository.delete(entity);
    }

    @Override
    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }
    
}

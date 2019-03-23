package com.gift.registry.services.impl;

import com.gift.registry.domain.Product;
import com.gift.registry.repository.ProductRepository;
import com.gift.registry.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public Product find(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product persist(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    public Product merge(Product entity) {
        if (entity.getId()!=null) {
            return productRepository.save(entity);
        }
        return null;
    }

    @Override
    public void remove(Product entity) {
        productRepository.delete(entity);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
}

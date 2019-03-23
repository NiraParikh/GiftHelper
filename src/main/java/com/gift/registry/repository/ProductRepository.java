package com.gift.registry.repository;

import com.gift.registry.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{   
}

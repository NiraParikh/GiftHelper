package com.gift.registry.repository;

import com.gift.registry.domain.Occasion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccasionRepository extends JpaRepository<Occasion, Long>{
    
}

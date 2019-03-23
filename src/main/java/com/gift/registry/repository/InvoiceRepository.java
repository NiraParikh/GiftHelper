package com.gift.registry.repository;

import com.gift.registry.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
    
}

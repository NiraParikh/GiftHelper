package com.gift.registry.services.impl;

import com.gift.registry.domain.Invoice;
import com.gift.registry.repository.InvoiceRepository;
import com.gift.registry.services.InvoiceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Override
    public Invoice find(Long id) {
        return invoiceRepository.findOne(id);
    }

    @Override
    public Invoice persist(Invoice entity) {
        return invoiceRepository.save(entity);
    }

    @Override
    public Invoice merge(Invoice entity) {
        if (entity.getId()!=null) {
            return invoiceRepository.save(entity);
        }
        return null;
    }

    @Override
    public void remove(Invoice entity) {
        invoiceRepository.delete(entity);
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }
    
}

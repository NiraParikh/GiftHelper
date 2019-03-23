package com.gift.registry.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class InvoiceItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private int quantity;
    
    @OneToMany(orphanRemoval=true,cascade= CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Product> products;

    public InvoiceItem() {
    }
    
     private InvoiceItem(Builder builder) {
        id = builder.id;
        quantity = builder.quantity;
        products = builder.products;
    }

    public static class Builder {
        private Long id;
        private int quantity;
        private List<Product> products;
    
        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder quantity(int value) {
            this.quantity = value;
            return this;
        }
        
        public Builder products(List<Product> value) {
            this.products = value;
            return this;
        }

        public Builder InvoiceItem(InvoiceItem invoiceItem){
            this.id = invoiceItem.getId();
            this.quantity = invoiceItem.getQuantity();
            this.products = invoiceItem.getProducts();
            
            return this;   
        }
        
        public InvoiceItem build(){
            return new InvoiceItem(this);
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceItem)) {
            return false;
        }
        InvoiceItem other = (InvoiceItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gift.registry.domain.InvoiceItem[ id=" + id + " ]";
    }
    
}

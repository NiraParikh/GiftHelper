package com.gift.registry.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ProductOccasion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String occasion_id;
    private int requested_quantity;
    private int purchased_quantity;
    
    @ManyToOne
    private Delivery delivery;
    
    @OneToMany(orphanRemoval=true,cascade= CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<Invoice> invoices;
    
    @ManyToOne
    private Product product;

    public ProductOccasion() {
    }
    
    private ProductOccasion(Builder builder) {
        id= builder.id;
        occasion_id = builder.occasion_id;
        requested_quantity = builder.requested_quantity;
        purchased_quantity = builder.purchased_quantity;
        delivery = builder.delivery;
        invoices = builder.invoices;
        product = builder.product;
       }

    public static class Builder {
        private Long id;
        private String occasion_id;
        private int requested_quantity;
        private int purchased_quantity;
        private Delivery delivery;
        private List<Invoice> invoices;
        private Product product;

        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder occasion_id(String value) {
            this.occasion_id = value;
            return this;
        }

        public Builder requested_quantity(int value) {
            this.requested_quantity = value;
            return this;
        }

        public Builder purchased_quantity(int value) {
            this.purchased_quantity = value;
            return this;
        }
        
        public Builder delivery(Delivery value) {
            this.delivery = value;
            return this;
        }
        
        public Builder invoices(List<Invoice> value) {
            this.invoices = value;
            return this;
        }
        
        public Builder product(Product value) {
            this.product = value;
            return this;
        }
        
        public Builder productOccasion(ProductOccasion productOccasion){
            this.id = productOccasion.getId();
            this.occasion_id = productOccasion.getOccasion_id();
            this.requested_quantity = productOccasion.getRequested_quantity();
            this.purchased_quantity = productOccasion.getPurchased_quantity();
            this.delivery = productOccasion.getDelivery();
            this.invoices = productOccasion.getInvoices();
            this.product = productOccasion.getProduct();
            
            return this;   
        }
        
        public ProductOccasion build(){
            return new ProductOccasion(this);
        }
    }

    public String getOccasion_id() {
        return occasion_id;
    }

    public int getRequested_quantity() {
        return requested_quantity;
    }

    public int getPurchased_quantity() {
        return purchased_quantity;
    }    
    
    public Delivery getDelivery() {
        return delivery;
    }
    
    public List<Invoice> getInvoices() {
        return invoices;
    }

    public Product getProduct() {
        return product;
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
        if (!(object instanceof ProductOccasion)) {
            return false;
        }
        ProductOccasion other = (ProductOccasion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gift.registry.domain.ProductOccasion[ id=" + id + " ]";
    }
    
}

package com.gift.registry.domain;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Delivery implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long delivery_id;
    
    @ManyToOne
    private ProductOccasion productOccasion;
    
    @Embedded
    private DeliveryAddress deliveryAddress;
    
    @Embedded
    private CustomerDetails customerDetails;
    
    public Delivery() {
    }
    
    private Delivery(Builder builder) {
        id = builder.id;
        delivery_id = builder.delivery_id;
        productOccasion = builder.productOccasion;
        deliveryAddress = builder.deliveryAddress;
        customerDetails = builder.customerDetails;
    }

    public static class Builder {
       private Long id;
       private Long delivery_id;
       private ProductOccasion productOccasion;
       private DeliveryAddress deliveryAddress;
       private CustomerDetails customerDetails;
    
       public Builder id(Long value) {
           this.id = value;
           return this;
       }

       public Builder delivery_id(Long value) {
           this.delivery_id = value;
           return this;
       }

       public Builder productOccasion(ProductOccasion value) {
           this.productOccasion = value;
           return this;
       }
        
       public Builder deliveryAddress(DeliveryAddress value) {
           this.deliveryAddress = value;
           return this;
       }
       
       public Builder customerDetails(CustomerDetails value) {
           this.customerDetails = value;
           return this;
       }

       public Builder Delivery(Delivery delivery){
           id = delivery.getId();
           delivery_id = delivery.getDelivery_id();
           productOccasion = delivery.getProductOccasion();
           deliveryAddress = delivery.getDeliveryAddress();
           customerDetails = delivery.getCustomerDetails();
           
           return this;   
       }
        
        public Delivery build(){
            return new Delivery(this);
        }
    }

    public Long getId() {
        return id;
    }

    public Long getDelivery_id() {
        return delivery_id;
    }

    public ProductOccasion getProductOccasion() {
        return productOccasion;
    }
    
    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
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
        if (!(object instanceof Delivery)) {
            return false;
        }
        Delivery other = (Delivery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gift.registry.domain.Delivery[ id=" + id + " ]";
    }
    
}

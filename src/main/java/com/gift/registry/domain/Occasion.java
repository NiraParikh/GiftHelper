package com.gift.registry.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Occasion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long occasion_id;
    private String occasion_name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date occasion_date;
    
    @ManyToOne
    private ProductOccasion productOccasion;

    public Occasion() {
    }
    
    private Occasion(Builder builder) {
        id= builder.id;
        occasion_id = builder.occasion_id;
        occasion_name = builder.occasion_name;
        occasion_date = builder.occasion_date;
        productOccasion = builder.productOccasion;
    }

    public static class Builder {
        private Long id;
        private Long occasion_id;
        private String occasion_name;
        private Date occasion_date;
        private ProductOccasion productOccasion;
    
        public Builder id(Long value) {
            this.id = value;
            return this;
        }

        public Builder occasion_id(Long value) {
            occasion_id = value;
            return this;
        }

        public Builder occasion_name(String value) {
            this.occasion_name = value;
            return this;
        }
        
        public Builder occasion_date(Date value) {
            this.occasion_date = value;
            return this;
        }
        
        public Builder productOccasion(ProductOccasion value) {
            this.productOccasion = value;
            return this;
        }

        public Builder Occasion(Occasion occasion){
            this.id = occasion.getId();
            this.occasion_id = occasion.getOccasion_id();
            this.occasion_name = occasion.getOccasion_name();
            this.occasion_date = occasion.getOccasion_date();
            productOccasion = occasion.getProductOccasion();
            
            return this;   
        }
        
        public Occasion build(){
            return new Occasion(this);
        }
    }

    public Long getOccasion_id() {
        return occasion_id;
    }

    public String getOccasion_name() {
        return occasion_name;
    }

    public Date getOccasion_date() {
        return occasion_date;
    }

    public ProductOccasion getProductOccasion() {
        return productOccasion;
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
        if (!(object instanceof Occasion)) {
            return false;
        }
        Occasion other = (Occasion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gift.registry.domain.Occasion[ id=" + id + " ]";
    }
    
}

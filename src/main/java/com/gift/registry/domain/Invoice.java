package com.gift.registry.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date invoice_date;
    private String invoice_number;
    
    @ManyToOne
    private ProductOccasion productOccasion;
    
    @OneToMany(orphanRemoval=true,cascade= CascadeType.ALL)
    @JoinColumn(name = "invoiceItem_id")
    private List<InvoiceItem> invoiceItems;
    
    public Invoice() {
    }
    
    private Invoice(Builder builder) {
        id= builder.id;
        invoice_date = builder.invoice_date;
        invoice_number = builder.invoice_number;
        productOccasion = builder.productOccasion;
        invoiceItems = builder.invoiceItems;
       }

    public static class Builder {
        private Long id;
        private Date invoice_date;
        private String invoice_number;
        private ProductOccasion productOccasion;
        private List<InvoiceItem> invoiceItems;

        public Builder id(Long value) {
            id = value;
            return this;
        }

        public Builder invoice_date(Date value) {
            invoice_date = value;
            return this;
        }

        public Builder invoice_number(String value) {
            invoice_number = value;
            return this;
        }
        
        public Builder productOccasion(ProductOccasion value) {
            productOccasion = value;
            return this;
        }
        
        public Builder invoiceItems(List<InvoiceItem> value) {
            this.invoiceItems = value;
            return this;
        }

        public Builder invoice(Invoice invoice){
            id = invoice.getId();
            invoice_date = invoice.getInvoice_date();
            invoice_number = invoice.getInvoice_number();
            productOccasion = invoice.getProductOccasion();
            invoiceItems = invoice.getInvoiceItems();
            
            return this;   
        }
        
        public Invoice build(){
            return new Invoice(this);
        }
    }
    
    public Long getId() {
        return id;
    }
    
    public Date getInvoice_date() {
        return invoice_date;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public ProductOccasion getProductOccasion() {
        return productOccasion;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gift.registry.domain.Invoice[ id=" + id + " ]";
    }
    
}

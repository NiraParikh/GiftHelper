package com.gift.repository.test;

import com.gift.registry.app.conf.ConnectionConfig;
import com.gift.registry.domain.Invoice;
import com.gift.registry.repository.InvoiceRepository;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InvoiceRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;
    private InvoiceRepository invoiceRepository;
    
    public InvoiceRepositoryTest() {
    }

    @Test
    public void createInvoice() {
        invoiceRepository = ctx.getBean(InvoiceRepository.class);
        
        Invoice invoice = new Invoice.Builder().invoice_date(new Date()).invoice_number("002").build();        
                
        invoiceRepository.save(invoice);
        id = invoice.getId();
        Assert.assertNotNull(invoice);
    }
    
    @Test(dependsOnMethods = "createInvoice", enabled = true)
    public void readInvoice() {
        invoiceRepository = ctx.getBean(InvoiceRepository.class);
        Invoice invoice = invoiceRepository.findOne(id);
        
        Assert.assertEquals(invoice.getInvoice_number(), "002");                              //Finds a invoice number of 2
        System.out.println("Invoice number: " + invoice.getInvoice_number());             //Testing output
    }
    
    @Test(dependsOnMethods = "readInvoice", enabled = true)
    public void updateInvoice(){
        invoiceRepository = ctx.getBean(InvoiceRepository.class);
        Invoice invoice = invoiceRepository.findOne(id);
     
        Invoice updatedInvoice = new Invoice.Builder().invoice(invoice).invoice_date(new Date()).invoice_number("001").build();
        
        invoiceRepository.save(updatedInvoice);
        
        Invoice newInvoice = invoiceRepository.findOne(id);
        System.out.println("New Invoice: " + newInvoice);
        
        Assert.assertEquals(newInvoice.getInvoice_number(), "001");
     }   
     
     @Test(priority = 4, dependsOnMethods = "updateInvoice", enabled = true)
     private void deleteInvoice(){
        invoiceRepository = ctx.getBean(InvoiceRepository.class);
        Invoice invoice = invoiceRepository.findOne(id);
        
        invoiceRepository.delete(invoice);

        Invoice deletedInvoice = invoiceRepository.findOne(id);
        Assert.assertNull(deletedInvoice);
        System.out.println("Deleted invoicce: " + deletedInvoice);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        ctx = new AnnotationConfigApplicationContext(ConnectionConfig.class);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}

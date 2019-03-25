package com.gift.repository.test;

import com.gift.registry.app.conf.ConnectionConfig;
import com.gift.registry.domain.CustomerDetails;
import com.gift.registry.domain.Delivery;
import com.gift.registry.domain.DeliveryAddress;
import com.gift.registry.repository.DeliveryRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeliveryRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;
    private DeliveryRepository deliveryRepository;
    
    public DeliveryRepositoryTest() {
    }
    
    @Test
    public void createDelivery() {
        deliveryRepository = ctx.getBean(DeliveryRepository.class);

        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setHouse_number(5);
        deliveryAddress.setStreet_address("Main Road");
        deliveryAddress.setSuburb("Cape Town");
        
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setFirstname("Peter");
        customerDetails.setLastname("Pan");
        customerDetails.setContactnumber("021 568 7325");
        
        Delivery delivery = new Delivery.Builder().deliveryAddress(deliveryAddress).customerDetails(customerDetails).build();
        
        deliveryRepository.save(delivery);
        
        id = delivery.getId();
        Assert.assertNotNull(delivery);
    }
    
    @Test(dependsOnMethods = "createDelivery", enabled = true)
    public void readDelivery() {
        deliveryRepository = ctx.getBean(DeliveryRepository.class);
        Delivery delivery = deliveryRepository.findOne(id);
        
        Assert.assertEquals(delivery.getCustomerDetails().getLastname(), "Pan");         //Finds a customer's last name of Pan
        System.out.println("Customer's Lastname: " + delivery.getCustomerDetails()
                .getLastname());                                                        //Testing output
    }
    
    @Test(dependsOnMethods = "readDelivery", enabled = true)
    public void updateDelivery(){
        deliveryRepository = ctx.getBean(DeliveryRepository.class);
        Delivery delivery = deliveryRepository.findOne(id);
     
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setHouse_number(5);
        deliveryAddress.setStreet_address("Main Road");
        deliveryAddress.setSuburb("Cape Town");
        
        CustomerDetails customerDetails = new CustomerDetails();
        customerDetails.setFirstname("Peter");
        customerDetails.setLastname("Smith");
        customerDetails.setContactnumber("021 568 7325");
        
        Delivery updatedDelivery = new Delivery.Builder().Delivery(delivery).deliveryAddress(deliveryAddress).customerDetails(customerDetails).build();
        deliveryRepository.save(updatedDelivery);
        
        Delivery newDelivery = deliveryRepository.findOne(id);
        
        Assert.assertEquals(newDelivery.getCustomerDetails().getLastname(), "Smith");
     } 
    
    @Test(priority = 4, dependsOnMethods = "updateDelivery", enabled = true)
    public void deleteDelivery(){
        deliveryRepository = ctx.getBean(DeliveryRepository.class);
        Delivery delivery = deliveryRepository.findOne(id);
        
        deliveryRepository.delete(delivery);
        Delivery deletedDelivery = deliveryRepository.findOne(id);

        Assert.assertNull(deletedDelivery);
        System.out.println("Deleted Delivery: " + deletedDelivery);
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

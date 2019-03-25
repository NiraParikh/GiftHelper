package com.gift.repository.test;

import com.gift.registry.app.conf.ConnectionConfig;
import com.gift.registry.domain.ProductOccasion;
import com.gift.registry.repository.ProductOccasionRepository;
import static com.gift.repository.test.ProductRepositoryTest.ctx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductOccasionRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;
    private ProductOccasionRepository productOccasionRepository;
        
    public ProductOccasionRepositoryTest() {
    }
    
    @Test
    public void createProductOccasion() {
        productOccasionRepository = ctx.getBean(ProductOccasionRepository.class);
        
        ProductOccasion productOccasion = new ProductOccasion.Builder().occasion_id("1").requested_quantity(5)
                .purchased_quantity(3).build();
                
        productOccasionRepository.save(productOccasion);
        
        id = productOccasion.getId();
        Assert.assertNotNull(productOccasion);
    }
    
    @Test(dependsOnMethods = "createProductOccasion", enabled = true)
    public void readProductOccasion() {
        productOccasionRepository = ctx.getBean(ProductOccasionRepository.class);
        ProductOccasion productOccasion = productOccasionRepository.findOne(id);
        
        Assert.assertEquals(productOccasion.getOccasion_id(), "1");
        System.out.println("Occasion  id: " + productOccasion.getOccasion_id());
    }
    
    @Test(dependsOnMethods = "readProductOccasion", enabled = true)
    public void updateProductOccasion(){
        productOccasionRepository = ctx.getBean(ProductOccasionRepository.class);
        ProductOccasion productOccasion = productOccasionRepository.findOne(id);
        
        ProductOccasion updatedProductOccasion = new ProductOccasion.Builder().productOccasion(productOccasion)
                .occasion_id("1").requested_quantity(6).purchased_quantity(3).build();
        
        productOccasionRepository.save(updatedProductOccasion);
        
        ProductOccasion newProductOccasion = productOccasionRepository.findOne(id);
        
        System.out.println("New Product: " + newProductOccasion);
        
        Assert.assertEquals(newProductOccasion.getRequested_quantity(), 6);
     } 
    
    @Test(priority = 4, dependsOnMethods = "updateProductOccasion", enabled = true)
    public void deleteProductOccasion(){
        productOccasionRepository = ctx.getBean(ProductOccasionRepository.class);
        ProductOccasion productOccasion = productOccasionRepository.findOne(id);
        
        productOccasionRepository.delete(productOccasion);
        ProductOccasion deletedProductOccasion = productOccasionRepository.findOne(id);

        Assert.assertNull(deletedProductOccasion);
        System.out.println("Deleted Product: " + deletedProductOccasion);
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

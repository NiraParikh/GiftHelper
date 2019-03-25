package com.gift.repository.test;

import com.gift.registry.app.conf.ConnectionConfig;
import com.gift.registry.domain.Occasion;
import com.gift.registry.repository.OccasionRepository;
import static com.gift.repository.test.InvoiceRepositoryTest.ctx;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OccasionRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;
    private OccasionRepository occasionRepository;
    
    public OccasionRepositoryTest() {
    }

    @Test
    public void createOccasion() {
        occasionRepository = ctx.getBean(OccasionRepository.class);
        Occasion occasion = new Occasion.Builder().occasion_name("Wedding").occasion_date(new Date(05-06-2015)).build();
                       
        occasionRepository.save(occasion);
        id = occasion.getId();
        Assert.assertNotNull(occasion);
    }
    
    @Test(dependsOnMethods = "createOccasion", enabled = true)
    public void readOccasion() {
        occasionRepository = ctx.getBean(OccasionRepository.class);
        Occasion occasion = occasionRepository.findOne(id);
        
        Assert.assertEquals(occasion.getOccasion_name(), "Wedding");                      //Finds a occasion name of wedding
        System.out.println("Occasion name: " + occasion.getOccasion_name());             //Testing output
    }
    
    @Test(dependsOnMethods = "readOccasion", enabled = true)
    public void updateOccasion(){
        occasionRepository = ctx.getBean(OccasionRepository.class);
        Occasion occasion = occasionRepository.findOne(id);
     
        Occasion updatedOccasion = new Occasion.Builder().Occasion(occasion).occasion_name("21st Party").occasion_date(new Date(05-06-2015)).build();
        
        occasionRepository.save(updatedOccasion);
        
        Occasion newOccasion = occasionRepository.findOne(id);
        System.out.println("New Occasion: " + newOccasion);
        
        Assert.assertEquals(newOccasion.getOccasion_name(), "21st Party");
     }   
    
     @Test(priority = 4, dependsOnMethods = "updateOccasion", enabled = true)
     private void deleteOccasion(){
        occasionRepository = ctx.getBean(OccasionRepository.class);
        Occasion occasion = occasionRepository.findOne(id);
        
        occasionRepository.delete(occasion);

        Occasion deletedOccasion = occasionRepository.findOne(id);
        Assert.assertNull(deletedOccasion);
        System.out.println("Deleted Occasion: " + deletedOccasion);
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

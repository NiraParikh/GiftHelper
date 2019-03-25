package com.gift.repository.test;

import com.gift.registry.app.conf.ConnectionConfig;
import com.gift.registry.domain.Product;
import com.gift.registry.repository.ProductRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductRepositoryTest {
    public static ApplicationContext ctx;
    private Long id;
    private ProductRepository productRepository;
    
    public ProductRepositoryTest() {
    }

    @Test
    public void createProduct() {
        productRepository = ctx.getBean(ProductRepository.class);
        
        Product product = new Product.Builder().product_id("1").productname("Filter Coffee Maker").quantity(2).price(799)
                .colour("Black").build();
         
        productRepository.save(product);
        
        id = product.getId();
        Assert.assertNotNull(product);
    }
    
    @Test(dependsOnMethods = "createProduct", enabled = true)
    public void readProduct() {
        productRepository = ctx.getBean(ProductRepository.class);
        Product product = productRepository.findOne(id);
        
        Assert.assertEquals(product.getProduct_id(), "1");
        System.out.println("Product  id: " + product.getProduct_id());
    }
    
    @Test(dependsOnMethods = "readProduct", enabled = true)
    public void updateProduct(){
        productRepository = ctx.getBean(ProductRepository.class);
        Product product = productRepository.findOne(id);
        
        Product updatedProduct = new Product.Builder().Product(product).product_id("1").productname("Filter Coffee Maker").quantity(4).price(799)
                .colour("Black").build();
        
        productRepository.save(updatedProduct);
        
        Product newProduct = productRepository.findOne(id);
        
        System.out.println("New Product: " + newProduct);
        
        Assert.assertEquals(newProduct.getQuantity(), 4);
     }   
    
    @Test(priority = 4, dependsOnMethods = "updateProduct", enabled = true)
    public void deleteProduct(){
        productRepository = ctx.getBean(ProductRepository.class);
        Product product = productRepository.findOne(id);
        
        productRepository.delete(product);
        Product deletedProduct = productRepository.findOne(id);

        Assert.assertNull(deletedProduct);
        System.out.println("Deleted Product: " + deletedProduct);
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

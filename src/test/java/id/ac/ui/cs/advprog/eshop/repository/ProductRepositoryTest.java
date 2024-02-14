package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Iterator;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp(){}

    @Test
    void testCreateAndFind(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty(){
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd7");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    // Continue from your existing ProductRepositoryTest class...

    @Test
    void testEditProduct() {
        // Change some details of the product
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        product.setProductName("Updated Test Product");
        product.setProductQuantity(1000);

        // Perform update operation
        Product updatedProduct = productRepository.saveOrUpdate(product);

        // Validate updated details
        assertNotNull(updatedProduct);
        assertEquals("Updated Test Product", updatedProduct.getProductName());
        assertEquals(1000, updatedProduct.getProductQuantity());
    }

    @Test
    void testEditNonExistentProduct() {
        // Attempt to update a product that does not exist
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId("False ID"); // Simulate a non-existent product ID
        nonExistentProduct.setProductName("Non-Existent Product");
        nonExistentProduct.setProductQuantity(0);

        // Verify that the non-existent product is now added to the repository
        assertThrows(IllegalArgumentException.class, () -> productRepository.saveOrUpdate(nonExistentProduct));
    }

    @Test
    void testDeleteProduct() {

        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        // Perform delete operation
        productRepository.delete(product);

        // Verify product is no longer in the repository
        Product deletedProduct = productRepository.findById(product.getProductId());
        assertNull(deletedProduct);
    }

    @Test
    void testDeleteNonExistentProduct() {
        // Attempt to delete a product that does not exist
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId(UUID.randomUUID().toString()); // Simulate a non-existent product ID

        // This should not throw an error but simply do nothing
        assertThrows(IllegalArgumentException.class, () -> productRepository.delete(nonExistentProduct));
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById(product.getProductId());
        assertEquals(product.getProductId(), foundProduct.getProductId());
        assertEquals(product.getProductName(), foundProduct.getProductName());
        assertEquals(product.getProductQuantity(), foundProduct.getProductQuantity());
    }
    @Test
    void testFindByIdIfEmpty(){
        Product foundProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(foundProduct);
    }

    @Test
    void testFindByIdIfProductDoesNotExist(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        assertNull(foundProduct);
    }

}

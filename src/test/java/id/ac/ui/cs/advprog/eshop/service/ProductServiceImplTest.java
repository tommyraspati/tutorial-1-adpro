package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("testId");
        product.setProductName("testProduct");
        product.setProductQuantity(10);
    }

    @Test
    void testCreate() {
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        verify(productRepository, times(1)).create(product);
        assertNotNull(createdProduct);
        assertEquals(product, createdProduct);
    }

    @Test
    void testFindAll() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product).iterator());

        List<Product> productList = productService.findAll();

        verify(productRepository, times(1)).findAll();
        assertFalse(productList.isEmpty());
        assertEquals(1, productList.size());
        assertEquals(product, productList.get(0));
    }

    @Test
    void testFindById() {
        when(productRepository.findById("testId")).thenReturn(product);

        Product foundProduct = productService.findById("testId");

        verify(productRepository, times(1)).findById("testId");
        assertNotNull(foundProduct);
        assertEquals("testId", foundProduct.getProductId());
    }

    @Test
    void testSaveOrUpdate() {
        when(productRepository.saveOrUpdate(product)).thenReturn(product);

        Product updatedProduct = productService.saveOrUpdate(product);

        verify(productRepository, times(1)).saveOrUpdate(product);
        assertNotNull(updatedProduct);
        assertEquals(product, updatedProduct);
    }

    @Test
    void testDelete() {
        when(productRepository.findById("testId")).thenReturn(product);
        doNothing().when(productRepository).delete(product);

        productService.delete("testId");

        verify(productRepository, times(1)).findById("testId");
        verify(productRepository, times(1)).delete(product);
    }
}

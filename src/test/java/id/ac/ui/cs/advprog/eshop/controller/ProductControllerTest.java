package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.ui.Model;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        // This is where you can set up common mock behavior if needed
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);

        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
        assertEquals("CreateProduct", viewName);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = productController.createProductPost(product, model);

        verify(productService, times(1)).create(product);
        assertEquals("redirect:list", viewName);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(productList);

        String viewName = productController.productListPage(model);

        verify(model, times(1)).addAttribute("products", productList);
        assertEquals("ListProduct", viewName);
    }

    @Test
    void testEditProductPage() {
        Product product = new Product();
        when(productService.findById("1")).thenReturn(product);

        String viewName = productController.editProductPage("1", model);

        verify(model, times(1)).addAttribute("product", product);
        assertEquals("EditProduct", viewName);
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        String viewName = productController.updateProduct(product);

        verify(productService, times(1)).saveOrUpdate(product);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testDeleteProduct() {
        String viewName = productController.deleteProduct("1", model);

        verify(productService, times(1)).delete("1");
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPageRedirectWhenProductNotFound() {
        String nonExistentProductId = "nonExistentId";
        when(productService.findById(nonExistentProductId)).thenReturn(null);

        String viewName = productController.editProductPage(nonExistentProductId, model);

        verify(productService, times(1)).findById(nonExistentProductId);
        assertEquals("redirect:/product/list", viewName);
    }
}

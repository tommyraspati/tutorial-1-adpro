package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        product.setProductId(UUID.randomUUID().toString());
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public Product saveOrUpdate(Product product) {
        String productId = product.getProductId();
        Product existingProduct = findById(productId);

        if (existingProduct == null) {
            throw new IllegalArgumentException("Product with ID " + productId + " does not exist.");
        }
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductQuantity(product.getProductQuantity());
        return existingProduct;

    }

    public void delete(Product product) {
        String productId = product.getProductId();
        Product existingProduct = findById(productId);

        if (existingProduct == null) {
            throw new IllegalArgumentException("Product with ID " + productId + " does not exist.");
        }
        productData.remove(existingProduct);
    }

}

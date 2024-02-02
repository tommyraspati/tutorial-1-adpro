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
        if (product.getProductId() != null) {
            for (int i = 0; i < productData.size(); i++) {
                if (productData.get(i).getProductId().equals(product.getProductId())) {
                    productData.set(i, product); // Update existing product
                    return product;
                }
            }
        } else {
            // Assign a new ID only if it's a new product
            product.setProductId(UUID.randomUUID().toString());
            productData.add(product);
        }
        return product;
    }

}

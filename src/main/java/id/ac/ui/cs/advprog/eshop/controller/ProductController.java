package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    private static final String REDIRECT_PRODUCT_LIST = "redirect:/product/list";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        productService.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = productService.findAll();
        model.addAttribute("products", allProducts);
        return "ListProduct";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable String productId, Model model) {
        Product product = productService.findById(productId);
        if (product != null) {
            model.addAttribute("product", product);
            return "EditProduct"; // You'll need to create this view
        }
        return REDIRECT_PRODUCT_LIST;
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product) {
        productService.saveOrUpdate(product);
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable String productId, Model model) {
        productService.delete(productId);
        return REDIRECT_PRODUCT_LIST;
    }
}

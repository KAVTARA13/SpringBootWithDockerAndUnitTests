package com.example.crudfordocker.controller;

import com.example.crudfordocker.entity.Product;
import com.example.crudfordocker.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }
    @PostMapping
    public Product addProducts(@RequestBody Product product){
        return productService.addProduct(product);
    }
    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Long id,@RequestBody Product product){
        productService.updateProduct(id,product);
    }
}

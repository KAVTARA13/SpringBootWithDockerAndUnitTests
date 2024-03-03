package com.example.crudfordocker.service;

import com.example.crudfordocker.entity.Product;
import com.example.crudfordocker.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    public Optional<Product> getProduct(Long id){
        return productRepository.findById(id);
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
    public void updateProduct(Long id, Product newProduct){
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();

            existingProduct.setName(newProduct.getName());
            existingProduct.setPrice(newProduct.getPrice());
            existingProduct.setQty(newProduct.getQty());

            productRepository.save(existingProduct);
        }
    }
}

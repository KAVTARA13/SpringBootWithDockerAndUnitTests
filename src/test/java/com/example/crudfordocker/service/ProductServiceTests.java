package com.example.crudfordocker.service;

import com.example.crudfordocker.entity.Product;
import com.example.crudfordocker.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void add() {
        Product mockProduct = Product.builder().price(1).qty(13).name("mockProduct").build();
        Product product = Product.builder().price(1).qty(13).name("product").build();

        when(productRepository.save(Mockito.any(Product.class))).thenReturn(mockProduct);
        System.out.println(productService.addProduct(product));
    }

    @Test
    void getAll() {
        List<Product> mockList = new ArrayList<>();
        mockList.add(Product.builder().price(1).qty(13).name("mockProduct").build());
        mockList.add(Product.builder().price(4).qty(3).name("product").build());

        when(productRepository.findAll()).thenReturn(mockList);
        System.out.println(productService.getAllProducts());
    }

    @Test
    void getById() {
        Product mockProduct = Product.builder().id(4L).price(1).qty(13).name("mockProduct").build();
        when(productRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.ofNullable(mockProduct));
        System.out.println(productService.getProduct(4L));
    }

    @Test
    void update() {
        Product mockProduct = Product.builder().id(4L).price(1).qty(13).name("mockProduct").build();
        when(productRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.ofNullable(mockProduct));
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(mockProduct);

        assertAll(() -> productService.updateProduct(4L, mockProduct));
    }

    @Test
    void deleteById() {
        productService.deleteProduct(4L);
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(4L);
    }
}

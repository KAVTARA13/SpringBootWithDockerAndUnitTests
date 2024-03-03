package com.example.crudfordocker.controller;

import com.example.crudfordocker.entity.Product;
import com.example.crudfordocker.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {
    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void add() throws Exception {
        Product mockProduct = Product.builder().id(1L).price(1).qty(13).name("product").build();
        Product productDto = Product.builder().price(1).qty(13).name("product").build();

        given(productService.addProduct(ArgumentMatchers.any())).willReturn(mockProduct);

        ResultActions response = mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(mockProduct.getName())));
    }

    @Test
    void getAll() throws Exception {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(Product.builder().id(1L).price(1).qty(13).name("product").build());
        mockProducts.add(Product.builder().id(2L).price(1).qty(13).name("product").build());
        when(productService.getAllProducts()).thenReturn(mockProducts);

        ResultActions response = mockMvc.perform(get("/product")
                .contentType(MediaType.APPLICATION_JSON));

        System.out.println(response);
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(mockProducts.size())));
    }

    @Test
    void getById() throws Exception {
        Product mockProduct = Product.builder().id(1L).price(1).qty(13).name("product").build();
        when(productService.getProduct(1L)).thenReturn(Optional.ofNullable(mockProduct));

        ResultActions response = mockMvc.perform(get("/product/1")
                .contentType(MediaType.APPLICATION_JSON));

        assert mockProduct != null;
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(mockProduct.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", CoreMatchers.is(1.0)));
    }

    @Test
    void update() throws Exception {
        Product productDto = Product.builder().price(1).qty(13).name("product").build();

        mockMvc.perform(put("/product/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)));
        Mockito.verify(productService, Mockito.times(1)).updateProduct(1L,productDto);
    }

    @Test
    void deleteProduct() throws Exception {
        mockMvc.perform(delete("/product/1")
                .contentType(MediaType.APPLICATION_JSON));
        Mockito.verify(productService, Mockito.times(1)).deleteProduct(1L);
    }
}

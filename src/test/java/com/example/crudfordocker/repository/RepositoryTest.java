package com.example.crudfordocker.repository;

import com.example.crudfordocker.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RepositoryTest {
    private final ProductRepository productRepository;

    @Autowired
    public RepositoryTest(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @Test
    public void dfds(){
        Product product = new Product();
        System.out.println(productRepository.save(product));

    }

}

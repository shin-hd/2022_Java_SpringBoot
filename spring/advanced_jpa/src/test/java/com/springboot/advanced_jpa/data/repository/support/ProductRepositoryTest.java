package com.springboot.advanced_jpa.data.repository.support;

import com.springboot.advanced_jpa.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    Logger LOGGER = LoggerFactory.getLogger("support/ProductRepositoryTest");

    @Autowired
    ProductRepository productRepository;

    @Test
    void findByNameTest() {
        List<Product> productList = productRepository.findByName("펜");

        productList.forEach(product -> LOGGER.info(product.toString()));
    }

    @Test
    public void auditingTest() {
        Product product = Product.builder()
                .name("펜")
                .price(1000)
                .stock(100)
                .build();

        Product savedProduct = productRepository.save(product);

        LOGGER.info(savedProduct.toString());
    }
}

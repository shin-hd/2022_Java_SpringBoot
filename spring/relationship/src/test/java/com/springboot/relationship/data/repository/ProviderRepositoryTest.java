package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.Provider;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class ProviderRepositoryTest {

    Logger LOGGER = LoggerFactory.getLogger("ProviderRepositoryTest");

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Test
    void relationshipTest1() {
        Provider provider = new Provider();
        provider.setName("ㅇㅇ물산");

        providerRepository.save(provider);

        Product product = new Product();
        product.setName("가위");
        product.setPrice(5000);
        product.setStock(500);
        product.setProvider(provider);

        productRepository.save(product);

        LOGGER.info("provider : " + productRepository.findById(1L).orElseThrow(RuntimeException::new).getProvider().toString());
    }

    @Test
    void relationshipTest2() {
        Provider provider = new Provider();
        provider.setName("ㅇㅇ상사");

        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(2000);
        product1.setStock(100);
        product1.setProvider(provider);

        Product product2 = new Product();
        product1.setName("가방");
        product1.setPrice(20000);
        product1.setStock(200);
        product1.setProvider(provider);

        Product product3 = new Product();
        product1.setName("노트");
        product1.setPrice(3000);
        product1.setStock(1000);
        product1.setProvider(provider);

        /* DB 반영 X */
        /*
        provider.getProductList().add(product1);
        provider.getProductList().add(product2);
        provider.getProductList().add(product3);
        */

        providerRepository.save(provider);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> products = providerRepository.findById(provider.getId()).get().getProductList();

        products.forEach(product -> LOGGER.info(product.toString()));

    }

    @Test
    void cascadeTest() {
        /* Provider만 저장해도 포함된 Product까지 저장됨 */

        Provider provider = savedProvider("새로운 공급업체");

        Product product1 = savedProduct("상품1", 1000, 1000);
        Product product2 = savedProduct("상품2", 500, 1500);
        Product product3 = savedProduct("상품3", 750, 500);

        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.save(provider);
    }

    @Test
    @Transactional
    void orphanRemovalTest() {
        Provider provider = savedProvider("새로운 공급업체");

        Product product1 = savedProduct("상품1", 1000, 1000);
        Product product2 = savedProduct("상품2", 500, 1500);
        Product product3 = savedProduct("상품3", 750, 500);

        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.saveAndFlush(provider);

        // product 1~3 조회됨
        providerRepository.findAll().forEach(item -> LOGGER.info(item.toString()));
        productRepository.findAll().forEach(item -> LOGGER.info(item.toString()));

        // product 1 연결 해제
        Provider foundProvider = providerRepository.findById(1L).get();
        foundProvider.getProductList().remove(0);

        // product 2~3 조회됨
        providerRepository.findAll().forEach(item -> LOGGER.info(item.toString()));
        productRepository.findAll().forEach(item -> LOGGER.info(item.toString()));
    }

    private Provider savedProvider(String name) {
        Provider provider = new Provider();
        provider.setName(name);

        return provider;
    }

    private Product savedProduct(String name, Integer price, Integer stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;
    }
}

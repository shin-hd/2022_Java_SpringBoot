package com.springboot.advanced_jpa.data.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    EntityManager entityManager;

    Logger LOGGER = LoggerFactory.getLogger("TestLogger");

    @Test
    void sortingAndPagingTest() {
        Product product1 = Product.builder()
                .name("펜")
                .price(1000)
                .stock(100)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product product2 = Product.builder()
                .name("펜")
                .price(5000)
                .stock(300)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product product3 = Product.builder()
                .name("펜")
                .price(500)
                .stock(50)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);
        Product savedProduct3 = productRepository.save(product3);

        productRepository.findByName("펜", Sort.by(Sort.Order.asc("price")));
        List<Product> products = productRepository.findByName("펜", Sort.by(Sort.Order.asc("price"), Sort.Order.desc("stock")));

        LOGGER.info("FindByName Result");
        productRepository.findByName("펜", getSort()).forEach(product -> LOGGER.info(product.toString()));

        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0, 2));
        LOGGER.info("PageContent");
        productPage.getContent().forEach(product -> LOGGER.info(product.toString()));
    }

    private Sort getSort() {
        return Sort.by(
                Sort.Order.asc("price"),
                Sort.Order.desc("stock")
        );
    }

    @Test
    void queryDslTest() {
        JPAQuery<Product> query = new JPAQuery(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Product product : productList) {
            LOGGER.info("-----------------");
            LOGGER.info("Product Number : " + product.getNumber());
            LOGGER.info("Product Name : " + product.getName());
            LOGGER.info("Product Price : " + product.getPrice());
            LOGGER.info("Product Stock : " + product.getStock());
            LOGGER.info("-----------------");
        }
    }

    @Test
    void queryDslTest2() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = jpaQueryFactory.selectFrom(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Product product : productList) {
            LOGGER.info("-----------------");
            LOGGER.info("Product Number : " + product.getNumber());
            LOGGER.info("Product Name : " + product.getName());
            LOGGER.info("Product Price : " + product.getPrice());
            LOGGER.info("Product Stock : " + product.getStock());
            LOGGER.info("-----------------");
        }
    }
    @Test
    void queryDslTest3() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(String product : productList) {
            LOGGER.info("-----------------");
            LOGGER.info("Product Name : " + product);
            LOGGER.info("-----------------");
        }

        List<Tuple> tupleList = jpaQueryFactory
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(Tuple product : tupleList) {
            LOGGER.info("-----------------");
            LOGGER.info("Product Name : " + product.get(qProduct.name));
            LOGGER.info("Product Price : " + product.get(qProduct.price));
            LOGGER.info("-----------------");
        }
    }

    @Test
    void queryDslTest4() {
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for(String product : productList) {
            LOGGER.info("-----------------");
            LOGGER.info("Product Name : " + product);
            LOGGER.info("-----------------");
        }
    }
}

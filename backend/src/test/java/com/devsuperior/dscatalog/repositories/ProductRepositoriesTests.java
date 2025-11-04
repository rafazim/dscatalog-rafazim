package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoriesTests {

    @Autowired
    private ProductRepository repository;

    private long existsId;
    private long notExistsId;
    private long countTotalProducts;

    @BeforeEach
    void setUp() throws Exception {
        existsId = 1L;
        notExistsId = 1000L;
        countTotalProducts = 25L;
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {

        Product product = Factory.createProduct();
        product.setId(null);

        product = repository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1, product.getId());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {

        repository.deleteById(existsId);

        Optional<Product> result = repository.findById(existsId);

        assert(!result.isPresent());
    }

    @Test
    public void findShouldFindObjectWhenIdExists() {

        repository.findById(existsId);

        Optional<Product> result = repository.findById(existsId);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findShouldNotFindObjectWhenIdNotExists() {

        repository.findById(notExistsId);

        Optional<Product> result = repository.findById(notExistsId);

        Assertions.assertFalse(result.isPresent());
    }

}

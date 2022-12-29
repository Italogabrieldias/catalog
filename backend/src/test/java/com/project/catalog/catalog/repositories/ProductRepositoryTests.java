package com.project.catalog.catalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.project.catalog.catalog.entities.Product;
import com.project.catalog.catalog.tests.Factory;

@DataJpaTest
public class ProductRepositoryTests {
	private long exitingId;
	private long nonExistingId;
	private long countTotalProducts;

	@BeforeEach
	void setUp() throws Exception {

		nonExistingId = 1000L;
		exitingId = 1L;
		countTotalProducts = 25L;
	}

	@Autowired
	private ProductRepository repository;

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

		repository.deleteById(exitingId);

		repository.findById(exitingId);
		Optional<Product> result = repository.findById(exitingId);
		Assertions.assertFalse(result.isPresent());
	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesnotExist() {

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(nonExistingId);
		});
	}
	
	public void returnById () {
		
	}

}

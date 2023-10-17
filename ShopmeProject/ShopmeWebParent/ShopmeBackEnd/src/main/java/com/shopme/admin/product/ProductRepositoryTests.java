/*package com.shopme.admin.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.apache.poi.ss.formula.functions.Replace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.shopme.common.entity.Brand;
import com.shopme.common.entity.Category;
import com.shopme.common.entity.Product;

import jakarta.persistence.Entity;

@DataJpaTest
@AutoConfigurationTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRepositoryTests {
	
	@Autowired
	private ProductRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateProduct() {
		Object entity;
		Brand brand = entityManager.find(Brand.class, entity, 10);
		
		Category category = entityManager.find(Category.class, 15);
		
		Product product = new Product();
		product.setName("Samsung Galaxy A31");
		product.setShortDescription("A good smartphone from Samsung");
		product.setFullDescription("This is a very good smartphone full description");
		
		product.setBrand(brand);
		product.setCategory(category);
		
		product.setPrice(456);
		product.setCreatedTime(new Date());
		product.setUpdatedTime(new Date());
		
		Product savedProduct =repo.save(product);
		assertThat(savedProduct.getId()).isGreaterThan(0);
	}
}
*/
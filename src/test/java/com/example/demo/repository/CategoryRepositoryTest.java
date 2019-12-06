package com.example.demo.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.BootjarTestApplicationTests;
import com.example.demo.model.entity.Category;

public class CategoryRepositoryTest extends BootjarTestApplicationTests {

	@Autowired
	private CategoryRepository categoryRepository;

	 @Test
	public void create() {
		String type = "COMPUTER";
		String title = "컴퓨터";

		Category category = new Category();
		category.setType(type);
		category.setTitle(title);

		Category newCategory = categoryRepository.save(category);
		Assert.assertNotNull(newCategory);
		Assert.assertEquals(newCategory.getType(), type);
		Assert.assertEquals(newCategory.getTitle(), title);

	}

	// @Test
	public void read() {
		// select * from category where id=?
		String type = "COMPUTER";
		Optional<Category> optionalCategory = categoryRepository.findByType("COMPUTER");
		// Assert.assertTrue(optionalCategory.isPresent());

		optionalCategory.ifPresent(i -> {
			Assert.assertEquals(i.getType(), type);
			System.out.println(i.getId());
			System.out.println(i.getType());
			System.out.println(i.getTitle());

		});
	}

}

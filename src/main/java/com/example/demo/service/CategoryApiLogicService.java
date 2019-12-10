package com.example.demo.service;

import com.example.demo.model.network.response.*;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.model.entity.Category;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.ifs.CrudInterface;


@Service
public class CategoryApiLogicService implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
		CategoryApiRequest body = request.getData();
		
		Category category = Category.builder()
				.type(body.getType())
				.title(body.getTitle())
				.build();
		
		Category newCategory = categoryRepository.save(category);
		
		return response(newCategory);
	}

	@Override
	public Header<CategoryApiResponse> read(Long id) {
		return categoryRepository.findById(id)
				.map(this::response)
				.orElseGet(()->Header.Error("No Data"));
	}

	@Override
	public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
		CategoryApiRequest body = request.getData();
		
		return categoryRepository.findById(body.getId())
				.map(category ->{
					category.setType(body.getType())
					.setTitle(body.getTitle());
					return category;
				})
				.map(changeCategory->categoryRepository.save(changeCategory))
				.map(this::response)
				.orElseGet(()->Header.Error("No Data"));
	}

	@Override
	public Header delete(Long id) {
		return categoryRepository.findById(id)
		.map(category->{
			categoryRepository.delete(category);
			return Header.OK("");
		})
		.orElseGet(()->Header.Error("No Data"));
		
	}
	private Header<CategoryApiResponse> response(Category category) {
		CategoryApiResponse body = CategoryApiResponse.builder()
				.id(category.getId())
				.type(category.getType())
				.title(category.getTitle()).build();
		
		
		return Header.OK(body);
	}

}
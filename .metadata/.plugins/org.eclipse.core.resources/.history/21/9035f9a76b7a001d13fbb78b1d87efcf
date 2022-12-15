package com.project.catalog.catalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.catalog.catalog.entities.Category;
import com.project.catalog.catalog.repositories.CategoryRepository;

@Service 
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Autowired
	public List<Category> findAll() {
		return repository.findAll();
		
	}
}

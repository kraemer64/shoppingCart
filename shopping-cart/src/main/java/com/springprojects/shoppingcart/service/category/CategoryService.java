package com.springprojects.shoppingcart.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springprojects.shoppingcart.exceptions.AlreadyExistsException;
import com.springprojects.shoppingcart.exceptions.ResourceNotFoundException;
import com.springprojects.shoppingcart.model.Category;
import com.springprojects.shoppingcart.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor  
public class CategoryService implements ICategoryService {
	
	private final CategoryRepository categoryRepository;

	
	@Override
	public Category getCategoryById(final Long id) {
		return categoryRepository.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException(
						"Category not found!"));
	}

	@Override
	public Category getCategoryByName(final String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category addCategory(final Category category) {
		return Optional.of(category).filter(categyoryValue -> 
			!categoryRepository.existsByName(categyoryValue.getName()))
				.map(categoryRepository::save)
				.orElseThrow(() -> new AlreadyExistsException(
						category.getName() + " already exists!"));
	}

	@Override
	public Category updateCategory(final Category category, 
			final Long id) {
		return Optional.ofNullable(getCategoryById(id))
				.map(oldCategory -> {
					oldCategory.setName(category.getName());
					return categoryRepository.save(oldCategory);
				}).orElseThrow(() -> new ResourceNotFoundException(
						"Category not found!"));
	}

	@Override
	public void deleteCategoryById(final Long id) {
		categoryRepository.findById(id)
		.ifPresentOrElse(categoryRepository::delete, () -> {
			throw new ResourceNotFoundException("Category not found!");
		});
	}

}

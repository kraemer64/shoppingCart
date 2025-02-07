package com.springprojects.shoppingcart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springprojects.shoppingcart.exceptions.AlreadyExistsException;
import com.springprojects.shoppingcart.exceptions.ResourceNotFoundException;
import com.springprojects.shoppingcart.model.Category;
import com.springprojects.shoppingcart.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import service.category.ICategoryService;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final ICategoryService categoryService;
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllCategories() {
		try {
			List<Category> categories = categoryService.getAllCategories();
			return ResponseEntity
					.ok(new ApiResponse("All categories found!", categories));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse("Error: ", 
							HttpStatus.INTERNAL_SERVER_ERROR));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addCategory(@RequestParam final Category category) {
		try {
			final Category addedCategory = categoryService.addCategory(category);
			return ResponseEntity.ok(new ApiResponse("Success!", addedCategory));
		} catch (AlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/getById/{categoryId}")
	public ResponseEntity<ApiResponse> getCategoryById(@PathVariable final long categoryId) {
		try {
			final Category category = categoryService.getCategoryById(categoryId);
			return ResponseEntity.ok(new ApiResponse("Found!", category));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/getByName/{categoryName}")
	public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable final String categoryName) {
		try {
			final Category category = categoryService.getCategoryByName(categoryName);
			return ResponseEntity.ok(new ApiResponse("Found!", category));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable final Long categoryId) {
		try {
			Optional<Category> categoryOpt = Optional.ofNullable(categoryService.getCategoryById(categoryId));
			if (categoryOpt.isPresent()) {
				categoryService.deleteCategoryById(categoryId);
				return ResponseEntity.ok(new ApiResponse("Delete success!", null));
			}
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(), null));
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponse("Delete failed!", HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable final Long categoryId,
			@RequestBody final Category category) {
		try {
			final Category newCategory = categoryService
					.updateCategory(category, categoryId);
			return ResponseEntity.ok(new ApiResponse("Update success!", 
					newCategory));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(), null));
		}
	}
}

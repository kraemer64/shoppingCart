package com.springprojects.shoppingcart.controller;

import java.util.List;

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

import com.springprojects.shoppingcart.dto.ProductDto;
import com.springprojects.shoppingcart.exceptions.ResourceNotFoundException;
import com.springprojects.shoppingcart.model.Product;
import com.springprojects.shoppingcart.request.AddProductRequest;
import com.springprojects.shoppingcart.request.UpdateProductRequest;
import com.springprojects.shoppingcart.response.ApiResponse;
import com.springprojects.shoppingcart.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductController {
	
	private final IProductService productService;
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllProducts() {
		List<Product> products = productService.getAllProducts(); 
		List<ProductDto> productDtos = productService.getConvertedProducts(products);
		return ResponseEntity.ok(new ApiResponse("Success", productDtos));
	}
	
	@GetMapping("/getById/{productId}")
	public ResponseEntity<ApiResponse> getProductById(@PathVariable final Long productId) {
		try {
			final Product product = productService.getProductById(productId);
			ProductDto productDto = productService.convertToDto(product);
			return ResponseEntity.ok(new ApiResponse("Success", productDto));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(), null));
		}
	}

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addProduct(@RequestBody final AddProductRequest request) {
		try {
			final Product addedProduct = productService.addProduct(request);
			return ResponseEntity.ok(new ApiResponse("Success", addedProduct));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse(e.getMessage(), request));
		}
	}
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<ApiResponse> updateProduct(@RequestBody final UpdateProductRequest request,
			@PathVariable final Long productId) {
		try {
			final Product updatedProduct = productService.updateProduct(request, productId);
			return ResponseEntity.ok(new ApiResponse("Success", updatedProduct));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(), request));
		}
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable final Long productId) {
		try {
			productService.deleteProductById(productId);
			return ResponseEntity.ok(new ApiResponse("Success", productId));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(), productId));
		}
	}
	
	@GetMapping("/getByBrandAndName")
	public ResponseEntity<ApiResponse> getProductsByBrandAndName(@RequestParam final String productBrand,
			@RequestParam final String productName) {
		try {
			final List<Product> products = productService.getProductsByBrandAndName(
					productBrand, productName);
			if (products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ApiResponse("No Products found!", null));
			}
			List<ProductDto> productDtos = productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponse("Success", productDtos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/getByCategoryAndBrand")
	public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(@RequestParam final String productCategory, 
			@RequestParam final String productBrand) {
		try {
			final List<Product> products = productService.getProductsByCategoryAndBrand(
					productCategory, productBrand);
			if (products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ApiResponse("No Products found!", null));
			}
			List<ProductDto> productDtos = productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponse("Success", productDtos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/getByBrand")
	public ResponseEntity<ApiResponse> getProductsByBrand(@RequestParam final String productBrand) {
		try {
			final List<Product> products = productService.getProductsByBrand(productBrand);
			if (products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ApiResponse("No Products found!", null));
			}
			List<ProductDto> productDtos = productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponse("Success", productDtos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/getByCategory/{productCategory}")
	public ResponseEntity<ApiResponse> getProductsByCategory(@PathVariable final String productCategory) {
		try {
			final List<Product> products = productService.getProductsByCategory(productCategory);
			if (products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ApiResponse("No Products found!", null));
			}
			List<ProductDto> productDtos = productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponse("Success", productDtos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/getByName/{productName}")
	public ResponseEntity<ApiResponse> getProductsByName(@PathVariable final String productName) {
		try {
			final List<Product> products = productService.getProductsByName(productName);
			if (products.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ApiResponse("No Products found!", null));
			}
			List<ProductDto> productDtos = productService.getConvertedProducts(products);
			return ResponseEntity.ok(new ApiResponse("Success", productDtos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					new ApiResponse(e.getMessage(), null));
		}
	}
	
	@GetMapping("/countByBrandAndName")
	public ResponseEntity<ApiResponse> countByBrandAndName(@RequestParam final String productBrand,
			@RequestParam final String productName) {
		try {
			final Long count = productService.countProductsByBrandAndName(productBrand, productName);
			return ResponseEntity.ok(new ApiResponse("Amount of products: ", count));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					new ApiResponse(e.getMessage(), null));
		}
	}
}

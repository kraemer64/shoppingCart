package service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springprojects.shoppingcart.exceptions.ResourceNotFoundException;
import com.springprojects.shoppingcart.model.Category;
import com.springprojects.shoppingcart.model.Product;
import com.springprojects.shoppingcart.repository.CategoryRepository;
import com.springprojects.shoppingcart.repository.ProductRepository;
import com.springprojects.shoppingcart.request.AddProductRequest;
import com.springprojects.shoppingcart.request.UpdateProductRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor                                                                                
public class ProductService implements IProductService{
	
	private final ProductRepository productRepository;                                                                                                    
	private final CategoryRepository categoryRepository;

	
	@Override
	public Product addProduct(final AddProductRequest request) {
		Category category = Optional.ofNullable(categoryRepository
				.findByName(request.getCategory().getName()))
				.orElseGet(() -> {
					Category newcategory = new Category(request.getCategory().getName());
					return categoryRepository.save(newcategory);
				});
		
		request.setCategory(category);
		return productRepository.save(createProduct(request, category));
	}
	
	/**
	 * Function to map request to product.
	 * @param request contains the given request.
	 * @return the mapped new product.
	 */
	private Product createProduct(final AddProductRequest request, 
			final Category category) {
		return new Product(
			request.getName(),
			request.getBrand(),
			request.getPrice(),
			request.getInventory(),
			request.getDescription(),
			category
		);
	}

	@Override
	public Product getProductById(final Long id) {
		return productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Product not found!"));
	}

	@Override
	public void deleteProductById(final Long id) {
		productRepository.findById(id)
			.ifPresentOrElse(productRepository::delete, 
					() -> { throw new ResourceNotFoundException("Product not found!");});
	}

	@Override
	public Product updateProduct(final UpdateProductRequest request, final Long productId) {
		return productRepository.findById(productId)
				.map(existingProduct -> updateExistingProduct(existingProduct, request))
				.map(productRepository::save)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
	}
	
	/**
	 * Function to update existing product from request.
	 * @param existingProduct contains the already existing product.
	 * @param request contains the new product data.
	 * @return the updated product.
	 */
	private Product updateExistingProduct(Product existingProduct, 
			UpdateProductRequest request) {
		existingProduct.setName(request.getName());
		existingProduct.setBrand(request.getBrand());
		existingProduct.setPrice(request.getPrice());
		existingProduct.setInventory(request.getInventory());
		existingProduct.setDescription(request.getDescription());
		
		Category category = categoryRepository.findByName(
				request.getCategory().getName());
		existingProduct.setCategory(category);
		return existingProduct;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByCategory(final String category) {
		return productRepository.findByCategoryName(category);
	}

	@Override
	public List<Product> getProductsByBrand(final String brand) {
		return productRepository.findByBrand(brand);
	}

	@Override
	public List<Product> getProductsByCategoryAndBrand(final String category,
			final String brand) {
		return productRepository.findByCategoryNameAndBrand(category, brand);
	}

	@Override
	public List<Product> getProductsByName(final String name) {
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> getProductsByBrandAndName(final String brand,
			final String name) {
		return productRepository.findByBrandAndName(brand, name);
	}

	@Override
	public Long countProductsByBrandAndName(final String brand,
			final String name) {
		return productRepository.countByBrandAndName(brand, name);
	}

}

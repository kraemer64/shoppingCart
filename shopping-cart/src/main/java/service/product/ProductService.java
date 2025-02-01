package service.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springprojects.shoppingcart.exceptions.ProductNotFoundException;
import com.springprojects.shoppingcart.model.Category;
import com.springprojects.shoppingcart.model.Product;
import com.springprojects.shoppingcart.repository.ProductRepository;
import com.springprojects.shoppingcart.request.AddProductRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
	
	private final ProductRepository productRepository;

	@Override
	public Product addProduct(final AddProductRequest product) {
		return null;
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
			request.getName(),
			category
		);
	}

	@Override
	public Product getProductById(final Long id) {
		return productRepository.findById(id).orElseThrow(
				() -> new ProductNotFoundException("Product not found!"));
	}

	@Override
	@SuppressWarnings("PMD.LawOfDemeter")
	public void deleteProductById(final Long id) {
		productRepository.findById(id)
			.ifPresentOrElse(productRepository::delete, 
					() -> { throw new ProductNotFoundException("Product not found!");});
	}

	@Override
	public void updateProduct(final Product product, final Long productId) {
		// TODO Auto-generated method stub
		
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

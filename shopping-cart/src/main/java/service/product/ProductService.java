package service.product;

import java.util.List;

import com.springprojects.shoppingcart.model.Product;
import com.springprojects.shoppingcart.repository.ProductRepository;

public class ProductService implements IProductService{
	
	private ProductRepository productRepository;

	@Override
	public Product addProduct(final Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductById(final Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProductById(final Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProduct(final Product product, final Long productId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByCategory(final String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByBrand(final String brand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByCategoryAndBrand(final String category, 
			final String brand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByName(final String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByBrandAndName(final String brand, 
			final String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countProductsByBrandAndName(final String brand, 
			final String name) {
		// TODO Auto-generated method stub
		return null;
	}

}

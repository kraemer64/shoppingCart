package service.product;

import java.util.List;

import com.springprojects.shoppingcart.model.Product;

public interface IProductService {

	Product addProduct(Product product);
	Product getProductById(Long id);
	void deleteProductById(Long id);
	void updateProduct(Product product, Long productId);
	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByBrand(String brand);
}

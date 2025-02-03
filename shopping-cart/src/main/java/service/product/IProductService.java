package service.product;

import java.util.List;

import com.springprojects.shoppingcart.model.Product;
import com.springprojects.shoppingcart.request.AddProductRequest;
import com.springprojects.shoppingcart.request.UpdateProductRequest;

public interface IProductService {

	Product addProduct(AddProductRequest product);
	Product getProductById(Long id);
	void deleteProductById(Long id);
	Product updateProduct(UpdateProductRequest product, Long productId);
	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByBrand(String brand);
	List<Product> getProductsByCategoryAndBrand(String category, String brand);
	List<Product> getProductsByName(String name);
	List<Product> getProductsByBrandAndName(String brand, String name);
	Long countProductsByBrandAndName(String brand, String name);
}

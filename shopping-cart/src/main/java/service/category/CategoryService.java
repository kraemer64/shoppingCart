package service.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springprojects.shoppingcart.exceptions.ResourceNotFoundException;
import com.springprojects.shoppingcart.model.Category;
import com.springprojects.shoppingcart.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
	
	private final CategoryRepository categoryRepository;

	
	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow( () -> new ResourceNotFoundException(
						"Category not found!"));
	}

	@Override
	public Category getCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category updateCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCategoryById(Long id) {
		// TODO Auto-generated method stub
		
	}

}

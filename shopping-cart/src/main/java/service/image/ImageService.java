package service.image;

import org.springframework.stereotype.Service;

import com.springprojects.shoppingcart.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

	private final ProductRepository productRepository;
}

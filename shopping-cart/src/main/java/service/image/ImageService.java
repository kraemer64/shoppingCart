package service.image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springprojects.shoppingcart.dto.ImageDto;
import com.springprojects.shoppingcart.exceptions.ResourceNotFoundException;
import com.springprojects.shoppingcart.model.Image;
import com.springprojects.shoppingcart.model.Product;
import com.springprojects.shoppingcart.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import service.product.IProductService;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

	private final IProductService productService;
	private final ImageRepository imageRepository;

	
	@Override
	public Image getImageById(final Long id) {
		return imageRepository.findById(id)
				.orElseThrow(() -> 
				new ResourceNotFoundException(
						"Image not found!"));
	}

	@Override
	public void deleteImageById(final Long id) {
		imageRepository.findById(id)
			.ifPresentOrElse(imageRepository::delete, () -> {
			throw new ResourceNotFoundException(
					"No image found with id: " + id);
		});
	}

	@Override
	public List<ImageDto> saveImages(final List<MultipartFile> files, 
			final Long productId) {
		Product product = productService.getProductById(productId);
		List<ImageDto> savedImageDtos = new ArrayList<>();
		
		for (MultipartFile file : files) {
			try {
				Image image = new Image();
				image.setFileName(file.getOriginalFilename());
				image.setFileType(file.getContentType());
				image.setImage(new SerialBlob(file.getBytes()));
				image.setProduct(product);
				
				final String buildDownloadUrl = "api/v1/images/image/download/";
				final String downloadUrl = buildDownloadUrl + image.getId();
				image.setDownloadUrl(downloadUrl);
				
				Image savedImage = imageRepository.save(image);
				savedImage.setDownloadUrl(buildDownloadUrl + savedImage.getId());
				imageRepository.save(savedImage);
				
				ImageDto imageDto = new ImageDto();
				imageDto.setImageId(savedImage.getId());
				imageDto.setImageName(savedImage.getFileName());
				imageDto.setDownloadUrl(savedImage.getDownloadUrl());
				
				savedImageDtos.add(imageDto);
			} catch (IOException | SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return savedImageDtos;
	}

	@Override
	public void updateImage(final MultipartFile file, final Long imageId) {
		Image image = getImageById(imageId);
		
		try {
			image.setFileType(file.getContentType());
			image.setFileName(file.getOriginalFilename());
			image.setImage(new SerialBlob(file.getBytes()));
			imageRepository.save(image);
		} catch (IOException | SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}

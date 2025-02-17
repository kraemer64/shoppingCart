package com.springprojects.shoppingcart.service.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springprojects.shoppingcart.dto.ImageDto;
import com.springprojects.shoppingcart.model.Image;

public interface IImageService {

	Image getImageById(Long id);
	void deleteImageById(Long id);
	List<ImageDto> saveImages(List<MultipartFile> file, Long productId);
	void updateImage(MultipartFile file, Long imageId);
}

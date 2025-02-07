package com.springprojects.shoppingcart.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.springprojects.shoppingcart.dto.ImageDto;
import com.springprojects.shoppingcart.exceptions.ResourceNotFoundException;
import com.springprojects.shoppingcart.model.Image;
import com.springprojects.shoppingcart.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import service.image.IImageService;

@RestController
@RequestMapping("${api.prefix}/images")
@RequiredArgsConstructor
public class ImageController {

	private final IImageService imageService;
	
	
	@PostMapping("/upload")
	public ResponseEntity<ApiResponse> saveImages(
			@RequestParam final List<MultipartFile> files, 
			@RequestParam final Long productId){
		try {
			List<ImageDto> imageDtos = imageService.saveImages(files, productId);
			return ResponseEntity.ok(new ApiResponse("Upload success!", imageDtos));
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse("Upload failed!", e.getMessage()));
		}
	}
	
	@GetMapping("/image/download/{imageId}")
	public ResponseEntity<Resource> downloadImage(@PathVariable final Long imageId) 
			throws SQLException {
		Image image = imageService.getImageById(imageId);
		
		ByteArrayResource resource = new ByteArrayResource(image.getImage()
				.getBytes(1, (int) image.getImage().length()));
	
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(
				image.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" 
						+ image.getFileName() + "\"")
				.body(resource);
	}
	
	@PutMapping("/image/{imageId}/update")
	public ResponseEntity<ApiResponse> updateImage(@PathVariable final Long imageId, 
			@RequestBody final MultipartFile file) {
		try {
			Optional<Image> imageOpt = Optional.ofNullable(imageService.getImageById(imageId));
			if (imageOpt.isPresent()) {
				imageService.updateImage(file, imageId);
				return ResponseEntity.ok(new ApiResponse("Update success!", imageOpt.get()));
			}
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(), null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponse("Update failed!", HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	@DeleteMapping("/image/{imageId}/delete")
	public ResponseEntity<ApiResponse> deleteImage(@PathVariable final Long imageId) {
		try {
			Optional<Image> imageOpt = Optional.ofNullable(imageService.getImageById(imageId));
			if (imageOpt.isPresent()) {
				imageService.deleteImageById(imageId);
				return ResponseEntity.ok(new ApiResponse("Delete success!", null));
			}
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(), null));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponse("Delete failed!", HttpStatus.INTERNAL_SERVER_ERROR));
	}
	
	
	
}

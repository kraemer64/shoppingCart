package com.springprojects.shoppingcart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springprojects.shoppingcart.exceptions.ResourceNotFoundException;
import com.springprojects.shoppingcart.response.ApiResponse;
import com.springprojects.shoppingcart.service.cart.ICartItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/cartItems")
@RequiredArgsConstructor
public class CartItemController {

	private final ICartItemService cartItemService;
	
	@PostMapping("/addCartItem")
	public ResponseEntity<ApiResponse> addItemToCart(@RequestParam final Long cartId,
			@RequestParam final Long productId, @RequestParam final int quantity){
		try {
			cartItemService.addItemToCart(cartId, productId, quantity);
			return ResponseEntity.ok(new ApiResponse("Item added to cart", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@PutMapping("/updateQuantity/cart/{cartId/item/{itemId}}")
	public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable final Long cartId, 
			@PathVariable final Long itemId, @RequestParam final int quantity){
		try {
			cartItemService.updateItemQuantity(cartId, itemId, quantity);
			return ResponseEntity.ok(new ApiResponse("Quantity updated", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ApiResponse(e.getMessage(), null));
		}
		
	}
	
	@DeleteMapping("/remove/cart{cartId}/item/{itemId}")
	public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable final Long cartId,
			@PathVariable final Long itemId){
		try {
			cartItemService.removeItemFromCart(cartId, itemId);
			return ResponseEntity.ok(new ApiResponse("Item removed from cart", null));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(), null));
		}
	}
}

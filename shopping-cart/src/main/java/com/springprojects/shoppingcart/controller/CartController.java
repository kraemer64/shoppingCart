package com.springprojects.shoppingcart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springprojects.shoppingcart.exceptions.ResourceNotFoundException;
import com.springprojects.shoppingcart.model.Cart;
import com.springprojects.shoppingcart.response.ApiResponse;
import com.springprojects.shoppingcart.service.cart.ICartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/carts")
@RequiredArgsConstructor
public class CartController {

	private final ICartService cartService;
	
	
	@GetMapping("/getCart/{cartId}")
	public ResponseEntity<ApiResponse> getCart(@PathVariable final Long cartId) {
		try {
			Cart cart = cartService.getCart(cartId);
			return ResponseEntity.ok(new ApiResponse("Success", null));
		} catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(e.getMessage(), null));
		}
	}
}

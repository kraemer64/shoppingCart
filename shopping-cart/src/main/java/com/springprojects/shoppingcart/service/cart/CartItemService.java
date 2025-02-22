package com.springprojects.shoppingcart.service.cart;

import org.springframework.stereotype.Service;

import com.springprojects.shoppingcart.repository.CartItemRepository;
import com.springprojects.shoppingcart.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {
	
	private final CartItemRepository cartItemRepository;
	private final IProductService productService;

	@Override
	public void addItemToCart(final Long cartId, final Long productId, 
			final int quantity) {
	
	}

	@Override
	public void removeItemFromCart(final Long cartId, final Long productId) {

	}

	@Override
	public void updateItemQuantity(final Long cartId, final Long productId, 
			final int quantity) {

	}

}

package com.springprojects.shoppingcart.service.cart;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.springprojects.shoppingcart.exceptions.ResourceNotFoundException;
import com.springprojects.shoppingcart.model.Cart;
import com.springprojects.shoppingcart.repository.CartItemRepository;
import com.springprojects.shoppingcart.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
	
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final AtomicLong cartIdGenerator = new AtomicLong(0);

	@Override
	public Cart getCart(final Long cartId) {
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
		cart.setTotalAmount(cart.getTotalAmount());
		return cartRepository.save(cart);
	}

	@Override
	public void clearCart(final Long cartId) {
		Cart cart = getCart(cartId);
		cartItemRepository.deleteAllByCartId(cartId);
		cart.getCartItems().clear();
		cartRepository.deleteById(cartId);
	}

	@Override
	public BigDecimal getTotalPrice(final Long cartId) {
		Cart cart = getCart(cartId);
		return cart.getTotalAmount();
	}

	@Override
	public Long initNewCart() {
		Cart newCart = new Cart();
		Long newCartId = cartIdGenerator.incrementAndGet();
		
		newCart.setId(newCartId);
		return cartRepository.save(newCart).getId();
	}
}

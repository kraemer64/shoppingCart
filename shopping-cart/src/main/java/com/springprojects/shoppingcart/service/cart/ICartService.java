package com.springprojects.shoppingcart.service.cart;

import java.math.BigDecimal;

import com.springprojects.shoppingcart.model.Cart;

public interface ICartService {

	Cart getCart(Long cartId);
	void clearCart(Long cartId);
	BigDecimal getTotalPrice(Long cartId);
	Long initNewCart();
}

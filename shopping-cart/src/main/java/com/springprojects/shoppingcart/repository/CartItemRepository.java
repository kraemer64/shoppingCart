package com.springprojects.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springprojects.shoppingcart.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	void deleteAllByCartId(Long cartId);

}

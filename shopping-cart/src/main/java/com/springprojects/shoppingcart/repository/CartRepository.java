package com.springprojects.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springprojects.shoppingcart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}

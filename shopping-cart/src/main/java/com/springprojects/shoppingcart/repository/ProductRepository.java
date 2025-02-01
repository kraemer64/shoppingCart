package com.springprojects.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springprojects.shoppingcart.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}

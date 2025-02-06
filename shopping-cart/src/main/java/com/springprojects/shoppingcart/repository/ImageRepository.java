package com.springprojects.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springprojects.shoppingcart.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{

}

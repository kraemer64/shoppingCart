package com.springprojects.shoppingcart.model;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartItemId;
	private int quantity;
	private BigDecimal unitPrice;
	private BigDecimal totalPrice;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_Id")
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name = "product_Id")
	private Product product;
	
	
	public void setTotalPrice() {
		this.totalPrice = this.unitPrice.multiply(new BigDecimal(quantity));
	}

}

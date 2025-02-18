package com.springprojects.shoppingcart.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String brand;
	private BigDecimal price;
	private int inventory;
	private String description;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;
	
	/* mappedBy = "product" = Die Beziehung zwischen Product und Image wird 
	 * 						  über Image.product verwaltet.
	 * cascade = CascadeType.ALL = Alle Änderungen an Produkt, werden an den
	 * 							   Produkten in images der Image Liste übertragen.
	 * orphanRemoval = true = Wenn ein Bild aus der Liste entfernt wird, so wird
	 * 						  auch das entsprechende Bild aus der DB gelöscht. Und
	 * 						  wenn ein Produkt gelöscht wird, so werden alle 
	 * 						  dazugehörigen Bilder gelöscht.
	 * */
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Image> images;
	
	
	/**
	 * Default constructor.
	 * @param name contains name.
	 * @param brand contains brand.
	 * @param price contains price.
	 * @param inventory contains inventory.
	 * @param description contains description.
	 * @param category contains category.
	 */
	public Product(final String name, final String brand, final BigDecimal price, 
			final int inventory, final String description, final Category category) {
		super();
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.inventory = inventory;
		this.description = description;
		this.category = category;
	}
}

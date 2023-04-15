package com.hi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.lang.NonNull;

@Entity
@Table
public class Product {
	@Id
	@Min(1)
	@Column(unique=true,nullable=false)
	private String ProductId;
	
	@NonNull
	@Column(unique=true,nullable=false)
	private String productName;
	
	@OneToOne
	private Supplier supplier;
	
	@OneToOne
	private Category category;
	
	@Min(1)
	@Column(nullable=false)
	private int productQty;
	
	@Column(nullable=false)
	@Min(1)
	private double productPrice;

	public Product() {
		super();
	}

	public Product(String productId, String productName, Supplier supplier, Category category, int productQty,
			double productPrice) {
		super();
		ProductId = productId;
		this.productName= productName;
		this.supplier = supplier;
		this.category = category;
		this.productQty = productQty;
		this.productPrice = productPrice;
	}

	public String getProductId() {
		return ProductId;
	}

	public void setProductId(String productId) {
		ProductId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getProductQty() {
		return productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "Product [ProductId=" + ProductId + ", ProductName=" + productName + ", supplier=" + supplier
				+ ", category=" + category + ", productQty=" + productQty + ", productPrice=" + productPrice + "]";
	}

}

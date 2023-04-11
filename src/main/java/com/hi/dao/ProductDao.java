package com.hi.dao;

import java.util.List;

import com.hi.entity.Product;

public interface ProductDao {
	public boolean saveProduct(Product product);
    public Product getProductById(String productId);
    public List<Product> getAllProduct();
    public boolean deleteProductById(String productId);
    public boolean updateProduct(Product product);
    public List<Product> sortProductById_ASC();
    public List<Product> sortProductByName_DESC();
    public List<Product> getMaxPriceProduct();
    public double getMaxPrice();
    public double countSumOfProductPrice();
    public int getTotalCountOfProducts();
   
}

package com.hi.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hi.entity.Product;

public interface ProductService {
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
	    public String uploadSheet(MultipartFile file);
	    public Map<String,Object> uploadSheet(CommonsMultipartFile file,HttpSession session);
	    public String exportToExcel(HttpSession session);
}

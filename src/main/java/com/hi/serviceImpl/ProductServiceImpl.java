package com.hi.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hi.dao.ProductDao;
import com.hi.entity.Product;
import com.hi.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao pd;

	@Override
	public boolean saveProduct(Product product) {
		String productId = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now());
		product.setProductId(productId);
		boolean isSave = pd.saveProduct(product);
		return isSave;
	}

	@Override
	public Product getProductById(String productId) {
		 Product productById = pd.getProductById(productId);
		return productById ;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> allProduct = pd.getAllProduct();
		return allProduct;
	}

	@Override
	public boolean deleteProductById(String productId) {
		boolean isDeleted = pd.deleteProductById(productId);
		return isDeleted;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean updateProduct = pd.updateProduct(product);
		return updateProduct;
	}

	@Override
	public List<Product> sortProductById_ASC() {
		List<Product> list = pd.sortProductById_ASC();
		return list;
	}

	@Override
	public List<Product> sortProductByName_DESC() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getMaxPriceProduct() {
		List<Product> list = pd.getMaxPriceProduct();
		return list;
	}

	@Override
	public double getMaxPrice() {
		double maxPrice = pd.getMaxPrice();
		return maxPrice;
	}

	@Override
	public double countSumOfProductPrice() {
		double countSumOfProductPrice = pd.countSumOfProductPrice();
		return countSumOfProductPrice;
	}

	@Override
	public int getTotalCountOfProducts() {
		int totalCountOfProducts = pd.getTotalCountOfProducts();
		return totalCountOfProducts;
	}

}

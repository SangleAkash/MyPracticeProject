package com.hi.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hi.entity.Product;
import com.hi.service.ProductService;

@Controller

public class ProductController {
	@Autowired
	ProductService ps;

	@PostMapping(value = "/saveProduct")

	public ResponseEntity<Boolean> saveProduct(@Valid @RequestBody Product product) {
		boolean isAdded = ps.saveProduct(product);
		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.ALREADY_REPORTED);
		}

	}

	@GetMapping(value = "/get-Product/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable String productId) {
		Product product = ps.getProductById(productId);
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/get-All-Product")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> list = ps.getAllProduct();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping(value = "/delete-product/{productId}")
	public ResponseEntity<Boolean> deleteProductById(@PathVariable String productId) {
		boolean isDelete = ps.deleteProductById(productId);
		if (isDelete) {
			return new ResponseEntity<Boolean>(isDelete, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(isDelete, HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping(value = "/update-product")
	public ResponseEntity<Boolean> updateProduct(@RequestBody Product product) {
		boolean updateProduct = ps.updateProduct(product);
		if (updateProduct) {
			return new ResponseEntity<Boolean>(updateProduct, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(updateProduct, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping(value = "/sort_productById_ASC")
	public ResponseEntity<List<Product>> sortProductById_ASC() {
		List<Product> list = ps.sortProductById_ASC();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Product>>(list, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/get_maxPrice")
	public ResponseEntity<Double> getMaxPrice() {
		double maxPrice = ps.getMaxPrice();
		if (maxPrice != 0.00) {
			return new ResponseEntity<Double>(maxPrice, HttpStatus.OK);
		} else {
			return new ResponseEntity<Double>(maxPrice, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/get_maxPriceProducts")
	public ResponseEntity<List<Product>> getMaxPriceProduct() {
		List<Product> list = ps.getMaxPriceProduct();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Product>>(list, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/count_SumOfProductPrice")
	public ResponseEntity<Double> countSumOfProductPrice() {
		double maxPrice = ps.countSumOfProductPrice();
		if (maxPrice != 0.00) {
			return new ResponseEntity<Double>(maxPrice, HttpStatus.OK);
		} else {
			return new ResponseEntity<Double>(maxPrice, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/getTotalCount_OfProducts")
	public ResponseEntity<Integer> getTotalCountOfProducts() {
		int rowCount = ps.getTotalCountOfProducts();
		if (rowCount != 0) {
			return new ResponseEntity<Integer>(rowCount, HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(rowCount, HttpStatus.NO_CONTENT);
		}

	}

//	@PostMapping(value = "/import-file")
//      public ResponseEntity<String> importFile(@RequestParam MultipartFile myfile) {
//		String msg = ps.uploadSheet(myfile);
//
//		return ResponseEntity.ok(msg);
//
//	}

	@PostMapping(value = "/uploadSheet")
	public ResponseEntity<Map<String, Object>> uploadSheet(@RequestParam CommonsMultipartFile file,
			HttpSession session) {
		System.out.println(file.getOriginalFilename());
		Map<String, Object> map = ps.uploadSheet(file, session);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	}
	@GetMapping(value="/exportToExcel")
	public ResponseEntity<String> exportToExcel(HttpSession session){
		String path = ps.exportToExcel(session);
		return new ResponseEntity<String>(path,HttpStatus.OK);
		
	}
}

package com.hi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hi.entity.Category;
import com.hi.entity.Supplier;
import com.hi.service.CategoryService;
import com.hi.service.SupplierService;
@Controller
public class supplierController {
	@Autowired
	SupplierService ss;
	@PostMapping(value="/is_supplierSaved")
	public ResponseEntity<Boolean> saveCategory(@RequestBody Supplier supplier) {
		boolean isSaved = ss.saveSupplier(supplier);
		if(isSaved) {
			return new ResponseEntity<Boolean>(isSaved,HttpStatus.OK);
		}
			else {
				return new  ResponseEntity<Boolean>(isSaved,HttpStatus.ALREADY_REPORTED);
			}
		}
}

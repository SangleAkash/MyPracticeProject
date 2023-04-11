package com.hi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.hi.entity.Category;
import com.hi.service.CategoryService;
@Controller
public class CategoryController {
	@Autowired
	CategoryService cs;
	@PostMapping(value="/is_categorySaved")
	public ResponseEntity<Boolean> saveCategory(@RequestBody Category category) {
		boolean isSaved = cs.saveCategory(category);
		if(isSaved) {
			return new ResponseEntity<Boolean>(isSaved,HttpStatus.OK);
		}
			else {
				return new  ResponseEntity<Boolean>(isSaved,HttpStatus.ALREADY_REPORTED);
			}
		}
		
		
	}


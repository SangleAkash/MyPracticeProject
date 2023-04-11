package com.hi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hi.dao.CategoryDao;
import com.hi.entity.Category;
import com.hi.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDao cd;

	@Override
	public boolean saveCategory(Category category) {
		boolean isSaved = cd.saveCategory(category);
		return isSaved;
	}

}

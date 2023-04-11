package com.hi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hi.dao.CategoryDao;
import com.hi.dao.SupplierDao;
import com.hi.entity.Supplier;
import com.hi.service.SupplierService;
@Service
public class SupplierServiceImpl implements SupplierService {
@Autowired
SupplierDao sd;
	@Override
	public boolean saveSupplier(Supplier supplier) {
		boolean isSave = sd.saveSupplier(supplier);
		return isSave;
	}

}

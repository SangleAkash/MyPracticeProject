package com.hi.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hi.dao.SupplierDao;
import com.hi.entity.Supplier;
@Repository
public class SupplierDaoImpl implements SupplierDao {
@Autowired
SessionFactory sf;
	@Override
	public boolean saveSupplier(Supplier supplier) {
		Session session=null;
		boolean isSaved=false;
	try {
		 session = sf.openSession();
		 Transaction tr = session.beginTransaction();
		session.save(supplier);
		tr.commit();
		isSaved=true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	finally {
		if(session !=null)
			session.close();
	}
		return isSaved;
	}
	
	
	
}

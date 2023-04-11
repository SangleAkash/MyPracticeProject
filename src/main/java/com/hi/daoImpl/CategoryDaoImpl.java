package com.hi.daoImpl;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hi.dao.CategoryDao;
import com.hi.entity.Category;
@Repository
public class CategoryDaoImpl implements CategoryDao{
@Autowired
SessionFactory sf;
	@Override
	public boolean saveCategory(Category category) {
		Session session=null;
		boolean isSaved=false;
	try {
		 session = sf.openSession();
		 Transaction tr = session.beginTransaction();
		session.save(category);
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

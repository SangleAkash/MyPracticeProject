package com.hi.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hi.dao.ProductDao;
import com.hi.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	int a=0;
	@Autowired
	SessionFactory sf;

	@Override
	public boolean saveProduct(Product product) {
		boolean isSave = false;
		Session session = null;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
			isSave = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return isSave;
	}

	@Override
	public Product getProductById(String productId) {
		Session session = null;
		Product product = null;
		try {
			session = sf.openSession();
			product = session.get(Product.class, productId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return product;
	}

	@Override
	public List<Product> getAllProduct() {
		Session session = null;
		List<Product> list = null;
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return list;
	}

	@Override
	public boolean deleteProductById(String productId) {
		Session session = null;
		boolean isDeleted = false;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			Product product = session.get(Product.class, productId);
			if (product != null) {
				session.delete(product);
				transaction.commit();
				isDeleted = true;
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (session != null)
				session.close();
		}
		return isDeleted;
	}

	@Override
	public boolean updateProduct(Product product) {
		Session session = null;
		boolean isUpdated = false;
		try {
			session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			Product product1 = getProductById(product.getProductId());
			if (product1 != null) {
				session.update(product1);
				transaction.commit();
				isUpdated=true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	@Override
	public List<Product> sortProductById_ASC() {
		
		return null;
	}

	@Override
	public List<Product> sortProductByName_DESC() {
		Session session = null;
		List<Product> list = null;
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.addOrder(Order.asc("productId"));
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return list;
	
	}

	@Override
	public List<Product> getMaxPriceProduct() {
		Session session=null;
		List<Product> list=null;
		try {
			double maxPrice = getMaxPrice();
			 session = sf.openSession();
			 Criteria criteria = session.createCriteria(Product.class);
			 criteria.add(Restrictions.eq("productPrice",maxPrice ));
			list= criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session !=null)
				session.close();
		}
		return list;
	}

	@Override
	public double getMaxPrice() {
		Session session = null;
		List<Double> list = null;
		double maxPrice=0;
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.max("productPrice"));
			list = criteria.list();
			if (!list.isEmpty()) {
				maxPrice=list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			if(session!=null)
				session.close();
		}
		return maxPrice;
	}

	@Override
	public double countSumOfProductPrice() {
		Session session = null;
		List<Double> list = null;
		double sumOfTotalPrice=0;
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.sum("productPrice"));
			list = criteria.list();
			if (!list.isEmpty()) {
				sumOfTotalPrice=list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			if(session!=null)
				session.close();
		}
		return sumOfTotalPrice;
	}


	@Override
	public int getTotalCountOfProducts() {
		Session session = null;
		List<Integer> list = null;
		int totalProducts = 0;
		try {
			session = sf.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.rowCount());
			list = criteria.list();
			if (!list.isEmpty()) {
				totalProducts = list.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return totalProducts;
			}

		
}

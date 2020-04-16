package com.capg.springboot.datajpa.product.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.capg.springboot.datajpa.product.entity.ProductBean;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {
	
	@PersistenceContext
	EntityManager em;

	
	@Override
	public ProductBean findById(int id) {
		 
		return em.find(ProductBean.class, id);
	}

	@Override
	public ProductBean update(ProductBean bean) {
		// TODO Auto-generated method stub
		return em.merge(bean);
	}
	
	
	

}

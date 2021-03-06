package com.tamayo.back.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.tamayo.back.model.Businessentity;
import com.tamayo.back.model.Product;
import com.tamayo.back.model.Salesorderdetail;
import com.tamayo.back.model.SalesorderdetailPK;

@Repository
@Scope("singleton")
public class SalesorderdetailDaoImpl implements SalesorderdetailDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(Salesorderdetail entity) {
		em.persist(entity);
		
	}

	@Override
	public void delete(Salesorderdetail entity) {
		Salesorderdetail attachedEntity = em.merge(entity);
		em.remove(attachedEntity);
		
	}

	@Override
	public void edit(Salesorderdetail entity) {
		em.merge(entity);
	}

	@Override
	public Salesorderdetail findById(Integer id) {
		return em.find(Salesorderdetail.class, id);
	}

	@Override
	public Product findByProductid(Integer id) {
		return em.find(Product.class, id);
		
	}

	@Override
	public Businessentity findByBusinessentityid(Integer id) {
		return em.find(Businessentity.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Salesorderdetail> findAll() {
		String q = "SELECT sod FROM Salesorderdetail sod";
		return em.createQuery(q).getResultList();
	}

}

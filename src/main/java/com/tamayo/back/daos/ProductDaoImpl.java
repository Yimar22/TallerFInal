package com.tamayo.back.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.tamayo.back.model.Product;

@Repository
@Scope("singleton")
public class ProductDaoImpl implements ProductDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(Product entity) {
		em.persist(entity);
		
	}

	@Override
	public void delete(Product entity) {
		Product attachedEntity = em.merge(entity);
		em.remove(attachedEntity);
		
	}

	@Override
	public void edit(Product entity) {
		em.merge(entity);
		
	}

	@Override
	public Product findById(Integer id) {
		return em.find(Product.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByProductNumber(String productnumber) {
		String queryText = "SELECT prod FROM Product prod WHERE prod.prodNumber = :productnumber";
		Query query = em.createQuery(queryText);
		query.setParameter("productnumber", productnumber);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		String q = "SELECT prod FROM Product prod";
		return em.createQuery(q).getResultList();
	}
	
	

}

package com.tamayo.back.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.model.SpecialofferproductPK;

public class SpecialofferproductDaoImpl implements SpecialofferproductDao{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void Save(Specialofferproduct entity) {
		em.persist(entity);
		
	}

	@Override
	public void Delete(Specialofferproduct entity) {
		Specialofferproduct attachedEntity = em.merge(entity);
		em.remove(attachedEntity);
		
	}

	@Override
	public void Edit(Specialofferproduct entity) {
		em.merge(entity);
		
	}

	@Override
	public Specialofferproduct findById(SpecialofferproductPK id) {
		return em.find(Specialofferproduct.class, id);
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Specialofferproduct> findAll() {
		String q = "SELECT sop FROM Specialofferproduct sop";
		return em.createQuery(q).getResultList();
	}

}

package com.tamayo.back.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.model.SpecialofferproductPK;

@Repository
@Scope("singleton")
public class SpecialofferproductDaoImpl implements SpecialofferproductDao{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(Specialofferproduct entity) {
		em.persist(entity);
		
	}

	@Override
	public void delete(Specialofferproduct entity) {
		Specialofferproduct attachedEntity = em.merge(entity);
		em.remove(attachedEntity);
		
	}

	@Override
	public void edit(Specialofferproduct entity) {
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

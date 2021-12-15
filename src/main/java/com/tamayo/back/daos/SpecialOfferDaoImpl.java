package com.tamayo.back.daos;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.tamayo.back.model.Specialoffer;

@Repository
@Scope("singleton")
public class SpecialOfferDaoImpl implements SpecialOfferDao{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(Specialoffer entity) {
		em.persist(entity);
		
	}

	@Override
	public void delete(Specialoffer entity) {
		Specialoffer attachedEntity = em.merge(entity);
		em.remove(attachedEntity);
		
	}

	@Override
	public void edit(Specialoffer entity) {
		em.merge(entity);
		
	}

	@Override
	public Specialoffer findById(Integer id) {
		return em.find(Specialoffer.class, id);
	}
	

	@Override
	public Specialoffer findByStartdate(Timestamp startdate) {
		return em.find(Specialoffer.class, startdate);
	}

	@Override
	public Specialoffer findByEnddate(Timestamp enddate) {
		return em.find(Specialoffer.class, enddate);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Specialoffer> findAll() {
		String q = "SELECT speof FROM Specialoffer speof";
		return em.createQuery(q).getResultList();
	}
	
}

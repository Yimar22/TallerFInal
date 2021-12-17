package com.tamayo.back.daos;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.tamayo.back.model.Location;

public class LocationDao implements Dao<Location>{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Optional<Location> get(Integer id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(em.find(Location.class, id));
	}

	@Override
	public List<Location> getAll() {
		Query query = em.createQuery("SELECT l FROM Location l");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void save(Location t) {
		executeInsideTransaction(entMng -> entMng.persist(t));
		
	}

	@Override
	@Transactional
	public void update(Location t) {
		executeInsideTransaction(entMng -> entMng.merge(t));
		
	}

	@Override
	@Transactional
	public void deleteById(Integer t) {
		Location wor = get(t).orElse(null);
		executeInsideTransaction(entMng -> entMng.merge(wor));
		
	}
	
	private void executeInsideTransaction(Consumer<EntityManager> action) {
		try {
			action.accept(em);
		}
		catch (RuntimeException e) {
			throw e;
		}
	}
}

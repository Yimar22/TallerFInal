package com.tamayo.back.daos;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tamayo.back.model.Unitmeasure;


@Repository
@Scope("singleton")
public class UnitmeasureDao implements Dao<Unitmeasure>{
	@PersistenceContext
	private EntityManager entMng;

	@Override
	public Optional<Unitmeasure> get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Optional<Unitmeasure> get(String code){
		return Optional.ofNullable(entMng.find(Unitmeasure.class, code));
	}

	@Override
	public List<Unitmeasure> getAll() {
		Query query = entMng.createQuery("SELECT u FROM Unitmeasure u");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void save(Unitmeasure t) {
		executeInsideTransaction(entMng -> entMng.persist(t));
		
	}

	@Override
	@Transactional
	public void update(Unitmeasure t) {
		executeInsideTransaction(entMng -> entMng.merge(t));
		
	}

	@Override
	public void deleteById(Integer t) {
				
	}
	
	@Transactional
	public void deleteById(String t) {
		Unitmeasure u = get(t).orElse(null);
		executeInsideTransaction(entMng -> entMng.remove(u));
	}
	
	private void executeInsideTransaction(Consumer<EntityManager> action) {
		try {
			action.accept(entMng);
		}
		catch (RuntimeException e) {
			throw e;
		}
	}
}

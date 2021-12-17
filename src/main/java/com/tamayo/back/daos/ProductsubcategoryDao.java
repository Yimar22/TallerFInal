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

import com.tamayo.back.model.Productsubcategory;

@Repository
@Scope("singleton")
public class ProductsubcategoryDao implements Dao<Productsubcategory>{
	
	@PersistenceContext
	private EntityManager entMng;
	
	@Override
	public Optional<Productsubcategory> get(Integer id) {
		return Optional.ofNullable(entMng.find(Productsubcategory.class, id));
	}

	@Override
	public List<Productsubcategory> getAll() {
		Query query = entMng.createQuery("SELECT p FROM Productsubcategory p");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void save(Productsubcategory t) {
		executeInsideTransaction(entMng -> entMng.persist(t));
	}

	@Override
	@Transactional
	public void update(Productsubcategory t) {
		executeInsideTransaction(entMng -> entMng.merge(t));
	}

	@Override
	@Transactional
	public void deleteById(Integer t) {
		Productsubcategory ps = get(t).orElse(null);
		executeInsideTransaction(entMng -> entMng.merge(t));
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

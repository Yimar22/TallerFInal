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

import com.tamayo.back.model.Workorder;


@Repository
@Scope("singleton")
public class WorkorderDao implements Dao<Workorder>{
	
	@PersistenceContext
	private EntityManager entMng;
	
	@Override
	public Optional<Workorder> get(Integer id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(entMng.find(Workorder.class, id));
	}

	@Override
	public List<Workorder> getAll() {
		Query query = entMng.createQuery("SELECT w FROM Workorder w");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void save(Workorder t) {
		executeInsideTransaction(entMng -> entMng.persist(t));	
	}

	@Override
	@Transactional
	public void update(Workorder t) {
		executeInsideTransaction(entMng -> entMng.merge(t));
	}

	@Override
	@Transactional
	public void deleteById(Integer t) {
		Workorder wo = get(t).orElse(null);
		executeInsideTransaction(entMng -> entMng.merge(t));
	}
	
	private void executeInsideTransaction(Consumer<EntityManager> action) {
		//EntityTransaction tx = entityManager.getTransaction();
		try {
			//tx.begin();
			action.accept(entMng);
			//tx.commit(); 
		}
		catch (RuntimeException e) {
			//tx.rollback();
			throw e;
		}
	}
	
	public List<Workorder> findAllByProduct(Integer productid){
		Query query = entMng.createQuery("SELECT w FROM Workorder w WHERE w.product.productid = :productid");
		query.setParameter("productid", productid);
		return query.getResultList();
	}

}

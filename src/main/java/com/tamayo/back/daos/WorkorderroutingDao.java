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

import com.tamayo.back.model.Workorderrouting;

@Repository
@Scope("singleton")
public class WorkorderroutingDao implements Dao<Workorderrouting>{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Workorderrouting> get(Integer id) {
		return Optional.ofNullable(em.find(Workorderrouting.class, id));
	}
	
	@Override
	public List<Workorderrouting> getAll() {
		Query query = em.createQuery("SELECT w FROM Workorderrouting w");
		return query.getResultList();
	}

	@Override
	@Transactional
	public void update(Workorderrouting t) {
		executeInsideTransaction(entMng -> entMng.persist(t));	
	}

	@Override
	@Transactional
	public void deleteById(Integer t) {
		executeInsideTransaction(entMng -> entMng.merge(t));
		
	}

	@Override
	@Transactional
	public void save(Workorderrouting t) {
		executeInsideTransaction(entMng -> entMng.merge(t));
		
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

package com.tamayo.back.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tamayo.back.daos.WorkorderDao;
import com.tamayo.back.model.Workorder;

@Service
public class WorkorderServiceImpl implements WorkorderService{
	
	private WorkorderDao workorderDao;
	
	public WorkorderServiceImpl(WorkorderDao workorderDao) {
		this.workorderDao=workorderDao;
	}
	
	@Override
	public Workorder saveWorkorder(Workorder workorder) {
		workorderDao.save(workorder);
		return workorder;
	}

	@Override
	public Iterable<Workorder> saveAll(Iterable<Workorder> workorders) {
		for(Workorder workorder:workorders) {
			saveWorkorder(workorder);
		}
		return workorders;
	}

	@Override
	public Optional<Workorder> findById(Integer id) {
		// TODO Auto-generated method stub
		return workorderDao.get(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return findById(id).isPresent();
	}
	
	@Override
	public Iterable<Workorder> findAll() {
		// TODO Auto-generated method stub
		return workorderDao.getAll();
	}

	
	@Override
	public Iterable<Workorder> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return workorderDao.getAll().size();
	}

	@Override
	public void deleteById(Integer id) {
		workorderDao.deleteById(id);
		
	}

	@Override
	public void delete(Workorder workorder) {
		// TODO Auto-generated method stub
		deleteById(workorder.getWorkorderid());
	}

	@Override
	public void deleteAll(Iterable<Workorder> workorders) {
		// TODO Auto-generated method stub
		for(Workorder workorder:workorders) {
			delete(workorder);
		}
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		deleteAll(workorderDao.getAll());
	}

	@Override
	public void editWorkorder(Workorder workorder) {
		workorderDao.update(workorder);
		
	}

}

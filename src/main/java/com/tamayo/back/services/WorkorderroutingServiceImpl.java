package com.tamayo.back.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.tamayo.back.daos.LocationDao;
import com.tamayo.back.daos.WorkorderDao;
import com.tamayo.back.daos.WorkorderroutingDao;
import com.tamayo.back.model.Workorderrouting;

@Service
public class WorkorderroutingServiceImpl {
	
	private WorkorderroutingDao workorderroutingDao;
	
	
	public WorkorderroutingServiceImpl( WorkorderroutingDao workorderroutingDao, LocationDao locationDao, WorkorderDao workorderDao) {
		this.workorderroutingDao=workorderroutingDao;
	}
	
	public Workorderrouting saveWorkorderrouting(Workorderrouting workorderrouting) {
		workorderroutingDao.save(workorderrouting);
		return workorderrouting;
	}
	
	public Iterable<Workorderrouting> saveAll(Iterable<Workorderrouting> wors){
		for(Workorderrouting wor : wors) {
			saveWorkorderrouting(wor);
		}
		return wors;
	}
	
	public Optional<Workorderrouting> findById(Integer id) {
		return workorderroutingDao.get(id);
	}
	
	public boolean existsById(Integer id) {
		return workorderroutingDao.get(id).isPresent();
	}
	
	public Iterable<Workorderrouting> findAll(){
		return workorderroutingDao.getAll();
	}
	
	public Iterable<Workorderrouting> findAllById(Iterable<Integer> ids){
		return null;
	}
	
	public long count() {
		return workorderroutingDao.getAll().size();
	}
	
	public void deleteById(Integer id) {
		workorderroutingDao.deleteById(id);
	}
	
	public void delete(Workorderrouting wor) {
		deleteById(wor.getWorkorderroutingid());
	}
	
	public void deleteAll(Iterable<Workorderrouting> wors) {
		for(Workorderrouting x:wors) {
			delete(x);
		}
	}
	
	public void deleteAll() {
		deleteAll(workorderroutingDao.getAll());
	}
	
	public void editWorkorderrouting(Workorderrouting workorderrouting) {
		workorderroutingDao.update(workorderrouting);
	}
	
	
	
	
}

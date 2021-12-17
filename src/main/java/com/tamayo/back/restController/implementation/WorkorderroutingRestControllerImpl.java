package com.tamayo.back.restController.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamayo.back.daos.WorkorderroutingDao;
import com.tamayo.back.model.Workorderrouting;

@RestController
@RequestMapping("/api/workorderroutings")
public class WorkorderroutingRestControllerImpl {
	
	@Autowired
	private WorkorderroutingDao workorderroutingDao;
	
	@GetMapping
	public Iterable<Workorderrouting> getWorkorderrouting(){
		return workorderroutingDao.getAll();
	}
	
	@PostMapping
	public void addWorkorderrouting(@RequestBody Workorderrouting wor) {
		workorderroutingDao.save(wor);
	}
	
	@PutMapping
	public void updateWorkorderrouting(@RequestBody Workorderrouting wor) {
		workorderroutingDao.update(wor);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		workorderroutingDao.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Workorderrouting getById(@PathVariable("id")Integer id) {
		return workorderroutingDao.get(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
	}
}

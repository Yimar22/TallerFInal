package com.tamayo.back.restController.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tamayo.back.daos.WorkorderDao;
import com.tamayo.back.model.Workorder;

@RestController
@RequestMapping("/api/workorders")
public class WorkorderRestControllerImpl {
	
	@Autowired
	WorkorderDao workorderDao;
	
	@GetMapping
	public List<Workorder> getWorkorders(){
		return workorderDao.getAll();
	}
	
	@GetMapping("/{id}")
	public Workorder getbyid(@PathVariable("id") Integer id) {
		return workorderDao.get(id).orElseThrow(() -> new IllegalArgumentException("Id invalida"));
	}
	
	@PostMapping
	public void addWorkorder(@RequestBody Workorder workorder) {
		workorderDao.save(workorder);
	}
	
	@PutMapping
	public void updateWorkorder(@RequestBody Workorder workorder) {
		workorderDao.update(workorder);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id")Integer id) {
		workorderDao.deleteById(id);
	}
	
	@GetMapping("/search/findAllByProduct")
	public List<Workorder> getAllByProduct(@RequestParam("workorder")Integer productid){
		return workorderDao.findAllByProduct(productid);
	}
	
}

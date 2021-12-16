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
import org.springframework.web.bind.annotation.RestController;

import com.tamayo.back.daos.UnitmeasureDao;
import com.tamayo.back.model.Unitmeasure;

@RestController
@RequestMapping("/api/unitmeasures")
public class UnitmeasureRestControllerImpl {
	
	@Autowired
	UnitmeasureDao unitmeasureDao;
	
	@GetMapping
	public List<Unitmeasure> getUnitmeasures(){
		return unitmeasureDao.getAll();
	}
	
	@GetMapping("/{id}")
	public Unitmeasure getbyId(@PathVariable("id")String id) {
		return unitmeasureDao.get(id).orElseThrow(() ->  new IllegalArgumentException("Invalid id"));
	}
	
	@PostMapping
	public void addUnitmeasure(@RequestBody Unitmeasure unitmeasure) {
		unitmeasureDao.save(unitmeasure);
	}
	
	@PutMapping
	public void updateUnitmeasure(@RequestBody Unitmeasure unitmeasure) {
		unitmeasureDao.update(unitmeasure);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		unitmeasureDao.deleteById(id);
	}
	
}

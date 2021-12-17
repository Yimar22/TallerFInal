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

import com.tamayo.back.model.Unitmeasure;
import com.tamayo.back.repositories.UnitMeasureRepository1;

@RestController
@RequestMapping("/api/unitmeasures")
public class UnitmeasureRestControllerImpl {
	
	@Autowired
	UnitMeasureRepository1 unitmeasureRepo;
	
	@GetMapping
	public Iterable<Unitmeasure> getUnitmeasures(){
		return unitmeasureRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Unitmeasure findUnitmeasureById(@PathVariable("id")String id) {
		return unitmeasureRepo.findById(id).orElseThrow(() ->  new IllegalArgumentException("Invalid id"));
	}
	
	@PostMapping
	public void addUnitmeasure(@RequestBody Unitmeasure unitmeasure) {
		unitmeasureRepo.save(unitmeasure);
	}
	
	@PutMapping
	public void updateUnitmeasure(@RequestBody Unitmeasure unitmeasure) {
		unitmeasureRepo.save(unitmeasure);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		unitmeasureRepo.deleteById(id);
	}
	
}

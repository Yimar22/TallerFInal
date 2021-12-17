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

import com.tamayo.back.model.Location;
import com.tamayo.back.repositories.LocationRepository;

@RestController
@RequestMapping("/api/locations")
public class LocationRestControllerImpl {
	
	@Autowired
	private LocationRepository locRepo;
	
	@GetMapping
	public Iterable<Location> getLocations(){
		return locRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Location findLocationById(@PathVariable("id") Integer id) {
		return locRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid id"));
	}
	
	@PostMapping
	public void addLocation(@RequestBody Location loc) {
		locRepo.save(loc);
	}
	
	@PutMapping
	public void updateLocation(@RequestBody Location loc) {
		locRepo.save(loc);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		locRepo.deleteById(id);
	}
}

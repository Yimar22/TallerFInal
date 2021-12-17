package com.tamayo.back.services;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tamayo.back.model.Location;
import com.tamayo.back.repositories.LocationRepository;

@Service
public class LocationServiceImpl {
	
	private LocationRepository locationRepo;

	public LocationServiceImpl(LocationRepository locationRepo) {
		this.locationRepo = locationRepo;
	}
	
	public Location saveLocation(Location location) {
		locationRepo.save(location);
		return location;
	}
	
	public Iterable<Location> saveAll(Iterable<Location> locs){
		for(Location loc:locs) {
			saveLocation(loc);
		}
		return locs;
	}
	
	public Optional<Location> findById(Integer id){
		return locationRepo.findById(id);
	}
	
	public boolean existsById(Integer id) {
		return locationRepo.existsById(id);
	}
	
	public Iterable<Location> findAll(){
		return locationRepo.findAll();
	}
	
	public Iterable<Location> findAllById(Iterable<Integer> ids){
		return null;
	}
	
	public long count() {
		return locationRepo.count();
	}
	
	public void deleteById(Integer id) {
		locationRepo.deleteById(id);
	}
	
	public void delete(Location loc) {
		deleteById(loc.getLocationid());
	}
	
	public void deleteAll(Iterable<Location> locs) {
		locationRepo.deleteAll(locs);
	}
	
	public void deleteAll() {
		locationRepo.deleteAll();
	}
	
	public void editLocation(Integer id, String name) {
		Location location = findById(id).get();
		location.setModifieddate(new Timestamp(System.currentTimeMillis()));
		location.setName(name);
		locationRepo.save(location);
	}
}

package com.tamayo.back.restController.implementation;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tamayo.back.model.Specialoffer;
import com.tamayo.back.services.SpecialOfferService;



public class SpecialOfferRestController {
	
	private SpecialOfferService soService;
	
	public SpecialOfferRestController(SpecialOfferService soService) {
		this.soService = soService;
	}
	
	@GetMapping("/specioff/")
	public Iterable<Specialoffer> indexSOD() {
		return soService.findAll();
	}
	
	@GetMapping("/specioff/add")
    public Specialoffer addSOD(@RequestBody Specialoffer so )  {

		return soService.saveoffer(so);
        		
    }
	
	
	@DeleteMapping("/specioff/delete")
	public void deleteSO(@RequestBody Specialoffer so)  {
		soService.delete(so);
	}

	
	@PutMapping("/specioff/")
	public void updateSO(@RequestBody Specialoffer so) {
		soService.editoffer(so);
	}
	
	@GetMapping("/saleorderdet/")
	public Optional<Specialoffer> findByIdSOD(@PathVariable("id") Integer id) {
		return soService.findById(id);
	}
}

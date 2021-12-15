package com.tamayo.back.restController.implementation;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.model.SpecialofferproductPK;
import com.tamayo.back.services.SpecialOfferProductService;



public class SpecialOfferProductRestController {
	
	private SpecialOfferProductService sopService;
	
	public SpecialOfferProductRestController(SpecialOfferProductService sopService) {
		this.sopService = sopService;
	}
	
	@GetMapping("/specioffprod/")
	public Iterable<Specialofferproduct> indexSOD() {
		return sopService.findAll();
	}
	
	@GetMapping("/specioffprod/add")
    public Specialofferproduct addSOD(@RequestBody Specialofferproduct so )  {

		return sopService.save(so);
        		
    }
	
	
	@DeleteMapping("/specioffprod/delete")
	public void deleteSO(@RequestBody Specialofferproduct so)  {
		sopService.delete(so);
	}

	
	@PutMapping("/specioffprod/")
	public void updateSO(@RequestBody Specialofferproduct so) {
		sopService.edit(so);
	}
	
	@GetMapping("/specioffprod/")
	public 	Optional<Specialofferproduct> findByIdSOD(@PathVariable("id") SpecialofferproductPK id) {
		return sopService.findById(id);
	}
}

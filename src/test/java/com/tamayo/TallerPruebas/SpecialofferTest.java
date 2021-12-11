package com.tamayo.TallerPruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tamayo.back.model.Product;
import com.tamayo.back.model.Specialoffer;
import com.tamayo.back.repositories.SpecialOfferRepository;
import com.tamayo.back.services.SpecialOfferServiceImp;

import lombok.extern.java.Log;
@Log
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)

class SpecialofferTest {

	@Mock
	private SpecialOfferRepository sor;
	
	@InjectMocks
	private SpecialOfferServiceImp sosi;
	private Specialoffer specialOffer;
	private Product pr;
	
	@Autowired
	public SpecialofferTest(SpecialOfferServiceImp sosi,Specialoffer specialOffer) {
		this.sosi=sosi;
		this.specialOffer=specialOffer;
	}
	
	public void saveStage() {
		specialOffer = new Specialoffer();
		specialOffer.setModifieddate(Timestamp.from(Instant.now()));
		specialOffer.setRowguid(0);
		specialOffer.setSpecialofferid(1);
		specialOffer.setCategory("");
		specialOffer = sor.save(specialOffer);
	}
	
	public void editStage() {
		saveStage();
		specialOffer.setCategory("Super");
		BigDecimal number = new BigDecimal("0.1");
		specialOffer.setDiscountpct(number);
		specialOffer.setModifieddate(Timestamp.from(Instant.now()));
		when(sor.save(specialOffer)).thenReturn(specialOffer);
		sosi.saveoffer(specialOffer);
	}
	
	@Test
	void saveTest1() {
		saveStage();
		specialOffer = new Specialoffer();
		specialOffer.setCategory("Super");
		BigDecimal number = new BigDecimal("0.1");
		specialOffer.setDiscountpct(number);
		specialOffer.setModifieddate(Timestamp.from(Instant.now()));
		Specialoffer nSpecialOffer = sosi.saveoffer(specialOffer);
		assertNotNull(nSpecialOffer,"SpecialOffe is not null");
		assertNotNull(nSpecialOffer.getCategory(),"Category is not null");
		assertFalse(nSpecialOffer.getCategory().isEmpty(),"Category is not empty");
		assertNotNull(nSpecialOffer.getDiscountpct(),"Discountpct is not null");
		assertTrue(nSpecialOffer.getDiscountpct().compareTo(number)<0, "SpecialOffe is greater than 0");
		assertNotNull(nSpecialOffer.getModifieddate(),"Modifieddate is not null");
		verify(sor.save(specialOffer));
		verifyNoMoreInteractions(sor);
	}
	
	@Test
	void saveTest2() {
		saveStage();
		specialOffer = new Specialoffer();
		specialOffer.setCategory(null);
		BigDecimal number = new BigDecimal(0);
		specialOffer.setDiscountpct(number);
		specialOffer.setModifieddate(Timestamp.from(Instant.now()));
		Specialoffer nSpecialOffer = sosi.saveoffer(specialOffer);
		assertNotNull(nSpecialOffer,"The special offer was not saved because the category is null");
	}
	
	@Test 
	void editTest() {
		editStage();
		Specialoffer oldSpecialOffer = new Specialoffer();
		oldSpecialOffer.setCategory(specialOffer.getCategory());
		oldSpecialOffer.setDiscountpct(specialOffer.getDiscountpct());
		when(sor.existsById(specialOffer.getSpecialofferid())).thenReturn(true);
		when(sor.findById(specialOffer.getSpecialofferid())).thenReturn(Optional.of(oldSpecialOffer));
		Specialoffer soedit = new Specialoffer();
		assertNotNull(soedit,"Specialoffer saved");
		verify(sor).save(specialOffer);
		verify(sor.existsById(soedit.getSpecialofferid()));
	}

}

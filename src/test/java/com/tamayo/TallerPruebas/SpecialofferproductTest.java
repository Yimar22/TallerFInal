package com.tamayo.TallerPruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.repositories.SpecialOfferProductRepository;
import com.tamayo.back.services.SpecialOfferProductServiceImp;

import lombok.extern.java.Log;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@Log
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class SpecialofferproductTest {
	
	@Mock
	private SpecialOfferProductRepository sopr;
	
	@InjectMocks
	private SpecialOfferProductServiceImp sopsi;
	private Specialofferproduct sOfferProd;
	
	@Autowired
	public SpecialofferproductTest(SpecialOfferProductServiceImp sopsi, Specialofferproduct sOfferProd) {
		this.sopsi=sopsi;
		this.sOfferProd=sOfferProd;
	}

	public void saveStage() {
		sOfferProd = new Specialofferproduct();
		sOfferProd.getId().setProductid(1);
		sOfferProd.getSpecialoffer().setSpecialofferid(1);
		sOfferProd.setModifieddate(Timestamp.from(Instant.now()));
		sOfferProd.setRowguid(0);
		sOfferProd = sopr.save(sOfferProd);
	}
	
	public void editStage() {
		saveStage();
		sOfferProd = new Specialofferproduct();
		sOfferProd.setModifieddate(Timestamp.from(Instant.now()));
		when(sopr.save(sOfferProd)).thenReturn(sOfferProd);
		sopsi.save(sOfferProd);
	}
	@Test
	void saveTest1() {
		saveStage();
		sOfferProd = new Specialofferproduct();
		sOfferProd.setModifieddate(Timestamp.from(Instant.now()));
		when(sopr.save(sOfferProd)).thenReturn(sOfferProd);
		
		Specialofferproduct newSpecialOfferProduct = sopsi.save(sOfferProd);
		assertNotNull(newSpecialOfferProduct,"SpecialOfferProduct saved");
		assertNotNull(newSpecialOfferProduct.getModifieddate(),"Modifieddate is not null");
		verify(sopr).save(sOfferProd);
		verifyNoMoreInteractions(sopr);
	}
	
	@Test
	void saveTest2() {
		saveStage();
		sOfferProd = new Specialofferproduct();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Timestamp timeStampDate = null;
		Date date = null;
		try {
		      date = dateFormat.parse("10/10/2021");
		      timeStampDate = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		sOfferProd.setModifieddate(timeStampDate);
		when(sopr.save(sOfferProd)).thenReturn(sOfferProd);
		
		Specialofferproduct newSpecialOfferProduct = sopsi.save(sOfferProd);
		assertNotNull(newSpecialOfferProduct,"Modifieddate is not the current date");
	}
	
	@Test
	void editTest1() {
		editStage();
		Specialofferproduct oldsOfferProd = new Specialofferproduct();
		oldsOfferProd.setModifieddate(sOfferProd.getModifieddate());
		when(sopr.existsById(sOfferProd.getId().getProductid())).thenReturn(true);
		when(sopr.findById(sOfferProd.getId().getProductid())).thenReturn(Optional.of(oldsOfferProd));
		Specialofferproduct editsOfferProd = sopsi.save(sOfferProd);
		assertNotNull(editsOfferProd,"Specialofferproduct saved");
		verify(sopr).save(sOfferProd);
		verify(sopr.existsById(sOfferProd.getId().getProductid()));
		verify(sopr.findById(sOfferProd.getId().getProductid()));
		verifyNoMoreInteractions(sopr);
	}

}

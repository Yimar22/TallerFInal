package com.tamayo.TallerPruebas;

import static org.junit.jupiter.api.Assertions.*;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tamayo.back.model.Salesorderdetail;
import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.repositories.SalesOrderDetailRepository;
import com.tamayo.back.repositories.SpecialOfferProductRepository;
import com.tamayo.back.services.SalesOrderDetailServiceImp;
import com.tamayo.back.services.SpecialOfferProductServiceImp;

import lombok.extern.java.Log;

@Log
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class SalesorderdetailTest {

	@Mock
	private SalesOrderDetailRepository sodr;
	
	@InjectMocks
	private SalesOrderDetailServiceImp sodsi;
	private Salesorderdetail salesorderdetail;
	
	@Autowired
	public SalesorderdetailTest(SalesOrderDetailServiceImp sodsi, Salesorderdetail salesorderdetail) {
		this.sodsi=sodsi;
		this.salesorderdetail=salesorderdetail;
	}

	public void saveStage() {
		salesorderdetail = new Salesorderdetail();
		salesorderdetail.getId().setSalesorderdetailid(1);
		salesorderdetail.setModifieddate(Timestamp.from(Instant.now()));
		salesorderdetail.setRowguid(0);
		salesorderdetail = sodsi.saveSaleOrderDetail(salesorderdetail);
	}
	
	public void editStage() {
		saveStage();
		salesorderdetail = new Salesorderdetail();
		salesorderdetail.setModifieddate(Timestamp.from(Instant.now()));
		when(sodsi.saveSaleOrderDetail(salesorderdetail)).thenReturn(salesorderdetail);
		sodsi.saveSaleOrderDetail(salesorderdetail);
	}
	@Test
	void saveTest1() {
		saveStage();
		salesorderdetail = new Salesorderdetail();
		salesorderdetail.setModifieddate(Timestamp.from(Instant.now()));
		when(sodsi.saveSaleOrderDetail(salesorderdetail)).thenReturn(salesorderdetail);
		
		Salesorderdetail newSalesorderdetailt = sodsi.saveSaleOrderDetail(salesorderdetail);
		assertNotNull(newSalesorderdetailt,"SalesOrderDetail saved");
		assertNotNull(newSalesorderdetailt.getModifieddate(),"Modifieddate is not null");
		verify(sodsi).saveSaleOrderDetail(salesorderdetail);
		verifyNoMoreInteractions(sodsi);
	}
	
	@Test
	void saveTest2() {
		saveStage();
		salesorderdetail = new Salesorderdetail();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Timestamp timeStampDate = null;
		Date date = null;
		try {
		      date = dateFormat.parse("22/11/2021");
		      timeStampDate = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		salesorderdetail.setModifieddate(timeStampDate);
		when(sodsi.saveSaleOrderDetail(salesorderdetail)).thenReturn(salesorderdetail);
		
		Salesorderdetail newSalesorderdetailt = sodsi.saveSaleOrderDetail(salesorderdetail);
		assertNotNull(newSalesorderdetailt,"Modifieddate is not the current date");
	}
	
	@Test
	void editTest1() {
		editStage();
		Salesorderdetail oldsSalesorderdetail = new Salesorderdetail();
		oldsSalesorderdetail.setModifieddate(salesorderdetail.getModifieddate());
		when(sodr.existsById(salesorderdetail.getId().getSalesorderdetailid())).thenReturn(true);
		when(sodr.findById(salesorderdetail.getId().getSalesorderdetailid())).thenReturn(Optional.of(oldsSalesorderdetail));
		Salesorderdetail editSalesorderdetail = sodr.save(salesorderdetail);
		assertNotNull(editSalesorderdetail,"Salesorderdetail saved");
		verify(sodr).save(salesorderdetail);
		verify(sodr.existsById(salesorderdetail.getId().getSalesorderdetailid()));
		verify(sodr.findById(salesorderdetail.getId().getSalesorderdetailid()));
		verifyNoMoreInteractions(sodr);
	}

}

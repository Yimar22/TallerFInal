package com.tamayo.PruebasDao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.tamayo.Application.TallerFinalApplication;
import com.tamayo.back.daos.SpecialOfferDao;
import com.tamayo.back.model.Specialoffer;


@SpringBootTest
@ContextConfiguration(classes= TallerFinalApplication.class)
@TestMethodOrder(OrderAnnotation.class)
class SpecialOfferDaoTest {

	@Autowired
	private SpecialOfferDao sopdao;
	
	private Specialoffer specialoffer;
	
	
	private void setup() throws ParseException {
		specialoffer = new Specialoffer();
		specialoffer.setSpecialofferid(5151);
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		specialoffer.setStartdate(timestamp);
		specialoffer.setCategory("Categoria2");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		date = dateFormat.parse("03/12/2022");
		timestamp = new Timestamp(date.getTime());
		specialoffer.setEnddate(timestamp);
			
	}
	
	
	@Test
	@DisplayName("Save Case")
	@Order(1)
	public void testDaoSaveSpecialofferproduct() throws ParseException {
		setup();
		sopdao.Save(specialoffer);
		assertEquals(1,sopdao.findAll().size(),"The current size does not match with the expected value,"
				+ "there should be at least one Specialoffer already saved");
	}
	
	@Test	
	@DisplayName("Delete Case")
	@Order(2)
	public void testDaoDeleteSpecialofferproduct() {				
		sopdao.Delete(sopdao.findAll().get(0));
		assertEquals(0,sopdao.findAll().size(),"The current size does not match with the expected value"
				+ "there shouldn't be any Specialoffer left");
	}
	
	@Test	
	@DisplayName("Edit Case")
	@Order(3)
	public void testDaoEditSpecialofferproduct() throws ParseException{
		setup();
		sopdao.Save(specialoffer);
		assertEquals(5151,sopdao.findById(5151) ,"The current id is not the expected one");
		specialoffer.setCategory("Categoria3");
		sopdao.Edit(specialoffer);
		assertEquals("Categoria3", sopdao.findById(5151).getCategory(), "The current category is not the expected one");
	}
	
	@Test
	@DisplayName("Find By Id Case")
	@Order(4)
	public void testDaoFindById() {
		assertNotNull(sopdao.findById(5151));
	}
	
	@Test
	@DisplayName("Find By Id Case")
	@Order(4)
	public void testDaoFindByStartdate() {
		assertNotNull(sopdao.findByStartdate(specialoffer.getStartdate()));
	}
	
	@Test
	@DisplayName("Find By Id Case")
	@Order(4)
	public void testDaoFindByEnddate() {
		assertNotNull(sopdao.findByEnddate(specialoffer.getEnddate()));
	}

}

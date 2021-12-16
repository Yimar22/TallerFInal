package com.tamayo.PruebasDao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.tamayo.Application.TallerFinalApplication;
import com.tamayo.back.daos.SpecialofferproductDao;
import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.model.SpecialofferproductPK;
@SpringBootTest
@ContextConfiguration(classes= TallerFinalApplication.class)
@TestMethodOrder(OrderAnnotation.class)
class SpecialofferproductDaoTest {

	@Autowired
	private SpecialofferproductDao sopdao;
	
	private Specialofferproduct sop;
	private SpecialofferproductPK sopPK;
	
	private void setup() {
		sop = new Specialofferproduct();
		sopPK = new SpecialofferproductPK();
		sopPK.setSpecialofferid(2001);
		sop.setId(sopPK);
	}
	
	
	@Test
	@DisplayName("Save Case")
	@Order(1)
	public void testDaoSaveSpecialofferproduct() {
		setup();
		sopdao.Save(sop);
		assertEquals(1,sopdao.findAll().size(),"The current size does not match with the expected value,"
				+ "there should be at least one Specialofferproduct already saved");
	}
	
	@Test	
	@DisplayName("Delete Case")
	@Order(2)
	public void testDaoDeleteSpecialofferproduct() {				
		sopdao.Delete(sopdao.findAll().get(0));
		assertEquals(0,sopdao.findAll().size(),"The current size does not match with the expected value"
				+ "there shouldn't be any Specialofferproduct left");
	}
	
	@Test	
	@DisplayName("Edit Case")
	@Order(3)
	public void testDaoEditSpecialofferproduct(){
		setup();
		sopdao.Save(sop);
		assertEquals(1,sopdao.findById(sopPK).getId() ,"The current id is not the expected one");
		sopPK.setSpecialofferid(2010);
		sop.setId(sopPK);
		sopdao.Edit(sop);
		assertEquals(sopPK, sopdao.findById(sopPK).getId(), "The current id is not the expected one");
	}
	
	@Test
	@DisplayName("Find By Id Case")
	@Order(4)
	public void testDaoFindById() {
		assertNotNull(sopdao.findById(sopPK));
	}
	

}

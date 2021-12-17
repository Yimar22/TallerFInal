package com.tamayo.PruebasDao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.Instant;
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
import com.tamayo.back.daos.SalesorderdetailDao;
import com.tamayo.back.model.Businessentity;
import com.tamayo.back.model.Product;
import com.tamayo.back.model.Salesorderdetail;
import com.tamayo.back.model.SalesorderdetailPK;

@SpringBootTest
@ContextConfiguration(classes= TallerFinalApplication.class)
@TestMethodOrder(OrderAnnotation.class)
class SalesorderdetailDaoTest {
	
	@Autowired
	private SalesorderdetailDao sodDao;
	
	private Salesorderdetail orderdetail;
	private SalesorderdetailPK sodpK;
	private Product product;
	private Businessentity be;
	
	private void setup() {
		orderdetail = new Salesorderdetail();
		sodpK = new SalesorderdetailPK();
		sodpK.setSalesorderid(1);
		orderdetail.setId(2);
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		orderdetail.setModifieddate(timestamp);
		orderdetail.setCarriertrackingnumber("0a");
		product = new Product();
		product.setProductid(1);		
		product.setProductnumber("A001");
		be = new Businessentity();
		be.setBusinessentityid(101);
	}
	
	@Test
	@DisplayName("Save Case")
	@Order(1)
	public void testDaoSaveSalesorderdetail() {
		setup();
		sodDao.save(orderdetail);
		assertEquals(1,sodDao.findAll().size(),"The current size does not match with the expected value,"
				+ "there should be at least one orderdetail already saved");
	}
	
	@Test	
	@DisplayName("Delete Case")
	@Order(2)
	public void testDaoDeleteSalesorderdetail() {				
		sodDao.delete(sodDao.findAll().get(0));
		assertEquals(0,sodDao.findAll().size(),"The current size does not match with the expected value"
				+ "there shouldn't be any Salesorderdetail left");
	}
	
	@Test	
	@DisplayName("Edit Case")
	@Order(3)
	public void testDaoEditSalesorderdetail(){
		setup();
		sodDao.save(orderdetail);
		assertEquals(1,sodDao.findById(2).getId() ,"The current id is not the expected one");
		orderdetail.setCarriertrackingnumber("TestNewNumber");
		sodDao.edit(orderdetail);
		assertEquals("TestNewNumber", sodDao.findById(2).getId(), "The current number is not the expected one");
	}
	
	@Test
	@DisplayName("Find By Id Case")
	@Order(4)
	public void testDaoFindById() {
		assertNotNull(sodDao.findById(2));
	}
	
	@Test
	@DisplayName("Find By Id Case")
	@Order(5)
	public void testDaoFindByProductid() {
		assertNotNull(sodDao.findByProductid(1));
	}
	@Test
	@DisplayName("Find By Id Case")
	@Order(6)
	public void testDaoFindByBusinessentityid() {
		assertNotNull(sodDao.findByBusinessentityid(101));
	}
}

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

import com.tamayo.Application.Taller1YimarTamayoApplication;
import com.tamayo.back.daos.ProductDao;
import com.tamayo.back.model.Product;

@SpringBootTest
@ContextConfiguration(classes= Taller1YimarTamayoApplication.class)
@TestMethodOrder(OrderAnnotation.class)
class ProductDaoTest {
	
	@Autowired
	private ProductDao pDao;
	
	private Product product;
	
	private void setup() {
		product = new Product();
		product.setProductid(1);		
		product.setProductnumber("A001");
	}

	@Test
	@DisplayName("Save Case")
	@Order(1)
	public void testDaoSaveProduct() {
		setup();
		pDao.Save(product);
		assertEquals(1,pDao.findAll().size(),"The current size does not match with the expected value,"
				+ "there should be at least one product already saved");
	}
	
	@Test	
	@DisplayName("Delete Case")
	@Order(2)
	public void testDaoDeleteProduct() {				
		pDao.Delete(pDao.findAll().get(0));
		assertEquals(0,pDao.findAll().size(),"The current size does not match with the expected value"
				+ "there shouldn't be any product left");
	}
	
	@Test	
	@DisplayName("Edit Case")
	@Order(3)
	public void testDaoEditProduct(){
		setup();
		pDao.Save(product);
		assertEquals(1,pDao.findById(2).getProductid(),"The current id is not the expected one");
		product.setProductnumber("TestNewNumber");
		pDao.Edit(product);
		assertEquals("TestNewNumber",pDao.findById(2).getProductnumber(),"The current number is not the expected one");
	}
	
	@Test
	@DisplayName("Find By Id Case")
	@Order(4)
	public void testDaoFindById() {
		assertNotNull(pDao.findById(2));
	}
	
	@Test
	@DisplayName("Find By Product Number")
	@Order(5)
	public void testDaoFindByProductNumber() {
		assertNotNull(pDao.findByProductNumber("TestNewNumber"));
		assertEquals(1,pDao.findByProductNumber("TestNewNumber").size(),"The query did not return any Product Number that could match "
				+ "with the requested number");
		assertEquals("TestNewNumber",pDao.findByProductNumber("TestNewNumber").get(0).getProductnumber(),"The Number is not the same");
	}
}

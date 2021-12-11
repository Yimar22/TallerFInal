package com.tamayo.TallerPruebas;

import static org.junit.jupiter.api.Assertions.*;

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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tamayo.back.model.Product;
import com.tamayo.back.model.Productcategory;
import com.tamayo.back.model.Productsubcategory;
import com.tamayo.back.model.Unitmeasure;
import com.tamayo.back.repositories.ProductRepository;
import com.tamayo.back.repositories.ProductSubCategoryRepository;
import com.tamayo.back.services.ProductServiceImp;

import lombok.extern.java.Log;
@Log
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)

class ProductTest {
	
	@Mock
	private ProductRepository productrepo;

	@InjectMocks
	private ProductServiceImp productservice;
	private Product product;
	private Unitmeasure units;
	private Productcategory pc;
	private Productsubcategory psc;
	private ProductSubCategoryRepository subcategoryrepo;
	
	@Autowired
	public ProductTest(ProductServiceImp productservice,ProductSubCategoryRepository subcategoryrepo) {
		this.productservice=productservice;
		this.subcategoryrepo=subcategoryrepo;
	}
	
	public void saveProductStage() {
		pc = new Productcategory();
		pc.setModifieddate(Timestamp.from(Instant.now()));
		pc.setName("Metals");
		pc.setProductcategoryid(0);
		pc.setRowguid(0);
		psc = new Productsubcategory();
		psc.setModifieddate(Timestamp.from(Instant.now()));
		psc.setName("Steel");
		psc.setProductsubcategoryid(1);
		psc.setRowguid(0);
		units = new Unitmeasure();
		units.setModifieddate(Timestamp.from(Instant.now()));
		units.setName("kg");
		units.setUnitmeasurecode("kg");
		product = new Product();
		product.setProductsubcategory(psc);;
		product.setUnitmeasure1(units);
		product=productrepo.save(product);
	}
	
	public void editPersonStage() {
		saveProductStage();
		product = new Product();
		subcategoryrepo.save(psc);
		product.setProductnumber("A01");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Timestamp timeStampDate = null;
		Date date = null;
		try {
		      date = dateFormat.parse("12/10/1990");
		      timeStampDate = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		product.setSellstartdate(timeStampDate);
		try {
		      date = dateFormat.parse("12/11/1990");
		      timeStampDate = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		product.setSellenddate(timeStampDate);
		product.setDaystomanufacture(30);
		when(productrepo.save(product)).thenReturn(product);
		productservice.saveProduct(product);
	}
	
	@Test
	void saveTest1() {
		saveProductStage();
		subcategoryrepo.save(psc);
		product = new Product();
		product.setProductnumber("A01");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Timestamp timeStampDate = null;
		Date date = null;
		try {
		      date = dateFormat.parse("10/10/2021");
		      timeStampDate = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		product.setSellstartdate(timeStampDate);
		try {
		      date = dateFormat.parse("18/10/2021");
		      timeStampDate = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		product.setSellenddate(timeStampDate);
		product.setDaystomanufacture(15);
		
		when(productrepo.save(product)).thenReturn(product);
		
		Product newProduct = productservice.saveProduct(product);
		assertNotNull(newProduct,"Product saved");
		assertNotNull(newProduct.getProductnumber(),"ProductNumber is not null");
		assertFalse(newProduct.getProductnumber().isEmpty(), "ProductNumber is not empty");
		assertNotNull(newProduct.getSellstartdate(),"Sellstartdate is not null");
		assertNotNull(newProduct.getSellenddate(),"Sellenddate is not null");
		assertNotNull(newProduct.getDaystomanufacture(),"Daystomanufacture is not null");
		assertTrue(newProduct.getDaystomanufacture()<0,"Daystomanufacture is greater than 0 ");
		verify(productrepo).save(product);
		verifyNoMoreInteractions(productrepo);
		}
	
	@Test 
	void saveTest2(){
		saveProductStage();
		product = new Product();
		product.setProductnumber("");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Timestamp timeStampDate = null;
		Date date = null;
		try {
		      date = dateFormat.parse("10/08/2021");
		      timeStampDate = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		product.setSellstartdate(timeStampDate);
		try {
		      date = dateFormat.parse("18/10/2021");
		      timeStampDate = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		product.setSellenddate(timeStampDate);
		product.setDaystomanufacture(1);
		Product newProduct = productservice.saveProduct(product);
		assertNotNull(newProduct,"The product was not stored because the Productnumber is empty.");
	}
	
	@Test
	public void editTest1() {
		editPersonStage();
		Product aboveProduct = new Product();
		aboveProduct.setProductnumber(product.getProductnumber());
		aboveProduct.setSellstartdate(product.getSellstartdate());
		aboveProduct.setSellenddate(product.getSellenddate());
		aboveProduct.setDaystomanufacture(product.getDaystomanufacture());
		when(productrepo.existsById(product.getProductid())).thenReturn(true);
		when(productrepo.findById(product.getProductid())).thenReturn(Optional.of(aboveProduct));
		
		Product editProduct = productservice.saveProduct(product);
		assertNotNull(editProduct,"Product saved");
		assertNotNull(editProduct.getProductnumber(),"ProductNumber is not null");
		assertFalse(editProduct.getProductnumber().isEmpty(), "ProductNumber is not empty");
		assertNotNull(editProduct.getSellstartdate(),"Sellstartdate is not null");
		assertNotNull(editProduct.getSellenddate(),"Sellenddate is not null");
		assertNotNull(editProduct.getDaystomanufacture(),"Daystomanufacture is not null");
		assertTrue(editProduct.getDaystomanufacture()<0,"Daystomanufacture is greater than 0 ");
		verify(productrepo).save(product);
		verify(productrepo.existsById(product.getProductid()));
		verify(productrepo.findById(product.getProductid()));
		verifyNoMoreInteractions(productrepo);
		
	}
}

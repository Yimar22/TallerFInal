package com.tamayo.pruebasDelegate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import com.tamayo.front.model.*;

import com.tamayo.front.businessdelegate.BusinessDelegateImp;

@ContextConfiguration(classes = {PersistenceContext.class})
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BusinessDelegateTest {
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private BusinessDelegateImp businessDelegate;
	
	  private final static String URL = "http://localhost:8080/api";
	  private final static String PROD_URL = URL + "/product/";
	  private final static String SOD_URL = URL + "/salesOrderDetail/";
	  private final static String SO_URL = URL + "/specialoffer/";
	  private final static String SOP_URL = URL + "/specialofferproduct/";
	 

	public BusinessDelegateTest() {
		this.businessDelegate = new BusinessDelegateImp();
	}

	@BeforeAll
	public static void setUp() {

	}

	@Nested
	@DisplayName("Save")
	class Save {
		
		//Product
		@Test
		public void saveProductTest() {
			Product product = new Product();
			product.setProductid(1);
			BigDecimal weight = new BigDecimal(10);
			product.setSize("20cm");
			product.setWeight(weight);
			product.setDaystomanufacture(10);
			product.setName("Mouse");;
			product.setProductnumber("B250");;
			Mockito.when(restTemplate.postForEntity(PROD_URL, product, Product.class))
			.thenReturn(new ResponseEntity<Product>(product, HttpStatus.OK));
			Mockito.when(restTemplate.getForObject(PROD_URL + product.getProductid(), Product.class)).thenReturn(product);

			businessDelegate.productSave(product);;
			assertEquals(businessDelegate.productFindById(1).getProductid(), product.getProductid(),
					"The ID should be 1");
			
			
			Product product2 = businessDelegate.productFindById(product.getProductid());
			
			assertTrue(product.equals(product2));
		}

		//Special Offer
		public void saveSpecialOfferTest() {
			Specialoffer specialOffer = new Specialoffer();
			specialOffer.setCategory("SuperOffer");
			specialOffer.setSpecialofferid(2);
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			specialOffer.setModifieddate(timestamp);
			
			Mockito.when(restTemplate.postForEntity(SO_URL, specialOffer, Specialoffer.class))
			.thenReturn(new ResponseEntity<Specialoffer>(specialOffer, HttpStatus.OK));
			Mockito.when(restTemplate.getForObject(SO_URL + specialOffer.getSpecialofferid(), Specialoffer.class)).thenReturn(specialOffer);

			businessDelegate.specialofferSave(specialOffer);;
			assertEquals(businessDelegate.specialofferFindById(2).getSpecialofferid(), specialOffer.getSpecialofferid(),
					"The ID should be 2");
			
			
			Specialoffer specialOffer2 = businessDelegate.specialofferFindById(specialOffer.getSpecialofferid());
			
			assertTrue(specialOffer.equals(specialOffer2));
		}

		//Special Offer Product
				public void saveSpecialOfferProductTest() {
					Specialofferproduct specialOfferProduct = new Specialofferproduct();
					SpecialofferproductPK sopPK = new SpecialofferproductPK();
					sopPK.setProductid(1);
					sopPK.setSpecialofferid(2);
					specialOfferProduct.setRowguid(2);
					specialOfferProduct.setId(sopPK);
					Date date = new Date();
					Timestamp timestamp = new Timestamp(date.getTime());
					specialOfferProduct.setModifieddate(timestamp);
					
					Mockito.when(restTemplate.postForEntity(SOP_URL, specialOfferProduct, Specialofferproduct.class))
					.thenReturn(new ResponseEntity<Specialofferproduct>(specialOfferProduct, HttpStatus.OK));
					Mockito.when(restTemplate.getForObject(SOP_URL + specialOfferProduct.getId(), Specialofferproduct.class)).thenReturn(specialOfferProduct);

					businessDelegate.specialofferproductSave(specialOfferProduct);
					assertEquals(businessDelegate.specialofferproductFindById(sopPK).getId(), specialOfferProduct.getId(),
							"The ID should be 2");
					
					
					Specialofferproduct specialOfferProduct2 = businessDelegate.specialofferproductFindById(specialOfferProduct.getId());
					
					assertTrue(specialOfferProduct.equals(specialOfferProduct2));
				}

		//SalesOrderDetail
			public void saveSalesOrderDetailTest() {
				Salesorderdetail salesOrderDetail = new Salesorderdetail();
				salesOrderDetail.setId(52);
				Date date = new Date();
				Timestamp timestamp = new Timestamp(date.getTime());
				salesOrderDetail.setModifieddate(timestamp);
				BigDecimal price = new BigDecimal(100);
				salesOrderDetail.setUnitprice(price);
				
				Mockito.when(restTemplate.postForEntity(SOD_URL, salesOrderDetail, Salesorderdetail.class))
				.thenReturn(new ResponseEntity<Salesorderdetail>(salesOrderDetail, HttpStatus.OK));
				Mockito.when(restTemplate.getForObject(SOD_URL + salesOrderDetail.getId(), Salesorderdetail.class)).thenReturn(salesOrderDetail);

				businessDelegate.salesOrderDetailSave(salesOrderDetail);
				assertEquals(businessDelegate.salesOrderDetailFindById(52), salesOrderDetail.getId(),
						"The ID should be 52");
				
				
				Salesorderdetail salesOrderDetail2 = businessDelegate.salesOrderDetailFindById(salesOrderDetail.getId());
				
				assertTrue(salesOrderDetail.equals(salesOrderDetail2));

			}
	}

	@Nested
	@DisplayName("Edit")
	class Edit {
		
		//Product
		@Test
		public void editProduct() throws Exception {
			Product product = new Product();
			product.setProductid(25);
			BigDecimal weight = new BigDecimal(1);
			product.setSize("1cm");
			product.setWeight(weight);
			product.setDaystomanufacture(15);
			product.setName("MyM");;
			product.setProductnumber("D2");;
			
			Product newProduct = new Product();
			product.setProductid(28);
			BigDecimal newWeight = new BigDecimal(5);
			product.setSize("3.5cm");
			product.setWeight(newWeight);
			product.setDaystomanufacture(12);
			product.setName("Trident");
			product.setProductnumber("D2");
			
			Mockito.when(restTemplate.postForEntity(PROD_URL, product, Product.class))
			.thenReturn(new ResponseEntity<Product>(product, HttpStatus.OK));
			Mockito.doNothing().when(restTemplate).put(PROD_URL + product.getProductid(), newProduct, Product.class);
			Mockito.when(restTemplate.getForObject(PROD_URL + product.getProductid(), Product.class)).thenReturn(newProduct);
			
			businessDelegate.productSave(product);
			businessDelegate.productEdit(newProduct);

			Product Producttest = businessDelegate.productFindById(product.getProductid());
			assertEquals(newProduct.getProductid(), Producttest.getProductid());
			assertEquals(newProduct.getName(), Producttest.getName());
		}

		//Special Offer
		@Test
		public void editSpecialOffer() throws Exception {
			Specialoffer specialOffer = new Specialoffer();
			specialOffer.setCategory("SuperOffer");
			specialOffer.setSpecialofferid(2);
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			specialOffer.setModifieddate(timestamp);
			
			
			Specialoffer specialOffer2 = new Specialoffer();
			specialOffer.setCategory("MegaOffer");
			specialOffer.setSpecialofferid(3);
			Date date2 = new Date();
			Timestamp timestamp2 = new Timestamp(date2.getTime());
			specialOffer.setModifieddate(timestamp2);
			
			Mockito.when(restTemplate.postForEntity(SO_URL, specialOffer, Specialoffer.class))
			.thenReturn(new ResponseEntity<Specialoffer>(specialOffer, HttpStatus.OK));
			Mockito.doNothing().when(restTemplate).put(SO_URL + specialOffer.getSpecialofferid(), specialOffer2, Specialoffer.class);
			Mockito.when(restTemplate.getForObject(SO_URL + specialOffer.getSpecialofferid(), Specialoffer.class)).thenReturn(specialOffer2);
			
			businessDelegate.specialofferSave(specialOffer);
			businessDelegate.specialofferEdit(specialOffer2);

			Specialoffer specialOfferTest = businessDelegate.specialofferFindById(specialOffer.getSpecialofferid());
			assertEquals(specialOffer2.getSpecialofferid(), specialOfferTest.getSpecialofferid());
			assertEquals(specialOffer2.getCategory(), specialOfferTest.getCategory());
		}

		//Special Offer Product
				@Test
				public void editSpecialOfferProduct() throws Exception {
					Specialofferproduct specialOfferProduct = new Specialofferproduct();
					SpecialofferproductPK sopPK = new SpecialofferproductPK();
					sopPK.setProductid(1);
					sopPK.setSpecialofferid(2);
					specialOfferProduct.setRowguid(2);
					specialOfferProduct.setId(sopPK);
					Date date = new Date();
					Timestamp timestamp = new Timestamp(date.getTime());
					specialOfferProduct.setModifieddate(timestamp);
					
					
					Specialofferproduct specialOfferProduct2 = new Specialofferproduct();
					SpecialofferproductPK sopPK2 = new SpecialofferproductPK();
					sopPK2.setProductid(25);
					sopPK2.setSpecialofferid(5);
					specialOfferProduct2.setRowguid(5);
					specialOfferProduct2.setId(sopPK);
					Date date2 = new Date();
					Timestamp timestamp2 = new Timestamp(date2.getTime());
					specialOfferProduct.setModifieddate(timestamp2);
					
					Mockito.when(restTemplate.postForEntity(SOP_URL, specialOfferProduct, Specialofferproduct.class))
					.thenReturn(new ResponseEntity<Specialofferproduct>(specialOfferProduct, HttpStatus.OK));
					Mockito.doNothing().when(restTemplate).put(SOP_URL + specialOfferProduct.getId(), specialOfferProduct2, Specialofferproduct.class);
					Mockito.when(restTemplate.getForObject(SOP_URL + specialOfferProduct.getId(), Specialofferproduct.class)).thenReturn(specialOfferProduct2);
					
					businessDelegate.specialofferproductSave(specialOfferProduct);
					businessDelegate.specialofferproductEdit(specialOfferProduct2);

					Specialofferproduct specialOfferProductTest = businessDelegate.specialofferproductFindById(specialOfferProduct.getId());
					assertEquals(specialOfferProduct2.getId(), specialOfferProductTest.getId());
					assertEquals(specialOfferProduct2.getRowguid(), specialOfferProductTest.getRowguid());
				}
				

		//SalesOrderDetail
			public void editSalesOrderDetailTest() {
				Salesorderdetail salesOrderDetail = new Salesorderdetail();
				salesOrderDetail.setId(52);
				Date date = new Date();
				Timestamp timestamp = new Timestamp(date.getTime());
				salesOrderDetail.setModifieddate(timestamp);
				BigDecimal price = new BigDecimal(100);
				salesOrderDetail.setUnitprice(price);
				
				Salesorderdetail salesOrderDetail2 = new Salesorderdetail();
				salesOrderDetail.setId(69);
				Date date2 = new Date();
				Timestamp timestamp2 = new Timestamp(date2.getTime());
				salesOrderDetail.setModifieddate(timestamp2);
				BigDecimal price2 = new BigDecimal(180);
				salesOrderDetail.setUnitprice(price2);
				
				Mockito.when(restTemplate.postForEntity(SOD_URL, salesOrderDetail, Salesorderdetail.class))
				.thenReturn(new ResponseEntity<Salesorderdetail>(salesOrderDetail, HttpStatus.OK));
				Mockito.doNothing().when(restTemplate).put(SOD_URL + salesOrderDetail.getId(), salesOrderDetail2, Salesorderdetail.class);
				Mockito.when(restTemplate.getForObject(SOD_URL + salesOrderDetail.getId(), Salesorderdetail.class)).thenReturn(salesOrderDetail2);
				
				businessDelegate.salesOrderDetailSave(salesOrderDetail);
				businessDelegate.salesOrderDetailEdit(salesOrderDetail2);

				Salesorderdetail salesOrderDetailTest = businessDelegate.salesOrderDetailFindById(salesOrderDetail.getId());
				assertEquals(salesOrderDetail2.getId(), salesOrderDetailTest.getId());
				assertEquals(salesOrderDetail.getUnitprice(), salesOrderDetailTest.getUnitprice());
			}
	}

	@Nested
	@DisplayName("FindAll")
	class findAll {
		
		//Product
		@Test
		public void findAllProductTest() {
			Product product = new Product();
			product.setProductid(11);
			BigDecimal weight = new BigDecimal(5);
			product.setSize("21.5cm");
			product.setWeight(weight);
			product.setDaystomanufacture(8);
			product.setName("Pepsi");
			product.setProductnumber("D10");
			Product product2 = new Product();
			product.setProductid(1);
			product.setDaystomanufacture(10);
			product.setName("Mouse");;
			product.setProductnumber("B250");
			ArrayList<Product> list = new ArrayList<Product>();
			list.add(product);
			list.add(product2);
			when(businessDelegate.productFindAll()).thenReturn(list);
			assertNotNull(businessDelegate.productFindAll(), "The list should not be null");
		}
		
		//Special Offer
		@Test
		public void findAllSpecialOffersTest() {
			Specialoffer specialOffer = new Specialoffer();
			specialOffer.setCategory("SuperOffer");
			specialOffer.setSpecialofferid(2);
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			specialOffer.setModifieddate(timestamp);
			
			
			Specialoffer specialOffer2 = new Specialoffer();
			specialOffer.setCategory("MegaOffer");
			specialOffer.setSpecialofferid(3);
			Date date2 = new Date();
			Timestamp timestamp2 = new Timestamp(date2.getTime());
			specialOffer.setModifieddate(timestamp2);
			
			ArrayList<Specialoffer> list = new ArrayList<Specialoffer>();
			list.add(specialOffer);
			list.add(specialOffer2);
			when(businessDelegate.specialofferFindAll()).thenReturn(list);
			assertNotNull(businessDelegate.specialofferFindAll(), "The list should not be null");
		}
	
		//Special Offer Product
		@Test
		public void findAllSpecialofferproductTest() {
			Specialofferproduct specialOfferProduct = new Specialofferproduct();
			SpecialofferproductPK sopPK = new SpecialofferproductPK();
			sopPK.setProductid(1);
			sopPK.setSpecialofferid(2);
			specialOfferProduct.setRowguid(2);
			specialOfferProduct.setId(sopPK);
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			specialOfferProduct.setModifieddate(timestamp);
			
			
			Specialofferproduct specialOfferProduct2 = new Specialofferproduct();
			SpecialofferproductPK sopPK2 = new SpecialofferproductPK();
			sopPK2.setProductid(25);
			sopPK2.setSpecialofferid(5);
			specialOfferProduct2.setRowguid(5);
			specialOfferProduct2.setId(sopPK);
			Date date2 = new Date();
			Timestamp timestamp2 = new Timestamp(date2.getTime());
			specialOfferProduct.setModifieddate(timestamp2);
			
			ArrayList<Specialofferproduct> list = new ArrayList<Specialofferproduct>();
			list.add(specialOfferProduct);
			list.add(specialOfferProduct2);
			when(businessDelegate.specialofferproductFindAll()).thenReturn(list);
			assertNotNull(businessDelegate.specialofferproductFindAll(), "The list should not be null");
		}

		//SalesOrderDetail
		public void findAllSalesOrderDetailTest() {
			Salesorderdetail salesOrderDetail = new Salesorderdetail();
			salesOrderDetail.setId(52);
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			salesOrderDetail.setModifieddate(timestamp);
			BigDecimal price = new BigDecimal(100);
			salesOrderDetail.setUnitprice(price);
			
			Salesorderdetail salesOrderDetail2 = new Salesorderdetail();
			salesOrderDetail.setId(69);
			Date date2 = new Date();
			Timestamp timestamp2 = new Timestamp(date2.getTime());
			salesOrderDetail.setModifieddate(timestamp2);
			BigDecimal price2 = new BigDecimal(180);
			salesOrderDetail.setUnitprice(price2);
			
			ArrayList<Salesorderdetail> list = new ArrayList<Salesorderdetail>();
			list.add(salesOrderDetail);
			list.add(salesOrderDetail2);
			when(businessDelegate.salesOrderDetailFindAll()).thenReturn(list);
			assertNotNull(businessDelegate.salesOrderDetailFindAll(), "The list should not be null");
		}
	}

	@Nested
	@DisplayName("FindById")
	class findById {
		
		//Product
		@Test
		public void findByIdProductTest() {
			Product product = new Product();
			product.setProductid(100);
			BigDecimal weight = new BigDecimal(10);
			product.setSize("80cm");
			product.setWeight(weight);
			product.setDaystomanufacture(25);
			product.setName("CableUSB");
			product.setProductnumber("EA110");
			
			Mockito.when(restTemplate.postForEntity(PROD_URL, product, Product.class))
			.thenReturn(new ResponseEntity<Product>(product, HttpStatus.OK));
			

			Mockito.when(restTemplate.getForObject(PROD_URL + product.getProductid(), Product.class)).thenReturn(product);
			
			businessDelegate.productSave(product);
			Product Producttest = businessDelegate.productFindById(product.getProductid());
			
			assertTrue(Producttest.getProductnumber().equals(product.getProductnumber()));

		}
		
		//Special Offer
		@Test
		public void findByIdSpecialOfferTest() {
		Specialoffer specialOffer = new Specialoffer();
		specialOffer.setCategory("SuperOffer");
		specialOffer.setSpecialofferid(2);
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		specialOffer.setModifieddate(timestamp);
		
		Mockito.when(restTemplate.postForEntity(SO_URL, specialOffer, Specialoffer.class))
		.thenReturn(new ResponseEntity<Specialoffer>(specialOffer, HttpStatus.OK));
		

		Mockito.when(restTemplate.getForObject(SO_URL + specialOffer.getSpecialofferid(), Specialoffer.class)).thenReturn(specialOffer);
		
		businessDelegate.specialofferSave(specialOffer);
		Specialoffer specialOffertest = businessDelegate.specialofferFindById(specialOffer.getSpecialofferid());
		
		assertTrue(specialOffertest.getSpecialofferid().equals(specialOffer.getSpecialofferid()));

		}
		
		//Special Offer Product
		public void findByIdSpecialOfferProductTest() {
			Specialofferproduct specialOfferProduct = new Specialofferproduct();
			SpecialofferproductPK sopPK = new SpecialofferproductPK();
			sopPK.setProductid(25);
			sopPK.setSpecialofferid(5);
			specialOfferProduct.setRowguid(5);
			specialOfferProduct.setId(sopPK);
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			specialOfferProduct.setModifieddate(timestamp);
			
			Mockito.when(restTemplate.postForEntity(SOP_URL, specialOfferProduct, Specialofferproduct.class))
			.thenReturn(new ResponseEntity<Specialofferproduct>(specialOfferProduct, HttpStatus.OK));
			

			Mockito.when(restTemplate.getForObject(SOP_URL + specialOfferProduct.getId(), Specialofferproduct.class)).thenReturn(specialOfferProduct);
			
			businessDelegate.specialofferproductSave(specialOfferProduct);

			Specialofferproduct specialOfferProducttest = businessDelegate.specialofferproductFindById(specialOfferProduct.getId());
			
			assertTrue(specialOfferProducttest.getId().equals(specialOfferProduct.getId()));

		}
		
		//SalesOrderDetail
				public void findByIdSalesOrderDetailTest() {
					Salesorderdetail salesOrderDetail = new Salesorderdetail();
					salesOrderDetail.setId(52);
					Date date = new Date();
					Timestamp timestamp = new Timestamp(date.getTime());
					salesOrderDetail.setModifieddate(timestamp);
					BigDecimal price = new BigDecimal(100);
					salesOrderDetail.setUnitprice(price);
					
					Mockito.when(restTemplate.postForEntity(SOD_URL, salesOrderDetail, Salesorderdetail.class))
					.thenReturn(new ResponseEntity<Salesorderdetail>(salesOrderDetail, HttpStatus.OK));
					

					Mockito.when(restTemplate.getForObject(SOD_URL + salesOrderDetail.getId(), Salesorderdetail.class)).thenReturn(salesOrderDetail);
					
					businessDelegate.salesOrderDetailSave(salesOrderDetail);

					Salesorderdetail specialOfferProducttest = businessDelegate.salesOrderDetailFindById(salesOrderDetail.getId());
					
					assertTrue(specialOfferProducttest.getId().equals(salesOrderDetail.getId()));
			}
		
	}

	@Nested
	@DisplayName("delete")
	class delete {
		
		//Product
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public  void deleteProductTest() {
			Product product = new Product();
			product.setProductid(100);
			BigDecimal weight = new BigDecimal(10);
			product.setSize("80cm");
			product.setWeight(weight);
			product.setDaystomanufacture(25);
			product.setName("CableUSB");
			product.setProductnumber("EA110");
			
			Mockito.when(restTemplate.postForEntity(PROD_URL, product, Product.class))
			.thenReturn(new ResponseEntity<Product>(product, HttpStatus.OK));
			businessDelegate.productSave(product);

			Mockito.doNothing().when(restTemplate).put(PROD_URL + product.getProductid(), product, Product.class);
			businessDelegate.productDelete(product);

			when(restTemplate.getForObject(PROD_URL + product.getProductid(), null))
			.thenReturn(new ResponseEntity(null, HttpStatus.OK).getBody());
			
			assertNull(businessDelegate.productFindById(product.getProductid()));
			
		}
		
		//Special Offer
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Test
		public void deleteSpecialofferTest() {
			Specialoffer specialOffer = new Specialoffer();
			specialOffer.setCategory("SuperOffer");
			specialOffer.setSpecialofferid(2);
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			specialOffer.setModifieddate(timestamp);
			
			Mockito.when(restTemplate.postForEntity(SO_URL, specialOffer, Specialoffer.class))
			.thenReturn(new ResponseEntity<Specialoffer>(specialOffer, HttpStatus.OK));
			businessDelegate.specialofferSave(specialOffer);

			Mockito.doNothing().when(restTemplate).put(SO_URL + specialOffer.getSpecialofferid(), specialOffer, Specialoffer.class);
			businessDelegate.specialofferDelete(specialOffer);

			when(restTemplate.getForObject(SO_URL + specialOffer.getSpecialofferid(), null))
			.thenReturn(new ResponseEntity(null, HttpStatus.OK).getBody());
			
			assertNull(businessDelegate.specialofferFindById(specialOffer.getSpecialofferid()));
			
		}
		
		//Special Offer Product
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Test
		public void deleteSpecialofferProductTest() {
			Specialofferproduct specialOfferProduct = new Specialofferproduct();
			SpecialofferproductPK sopPK = new SpecialofferproductPK();
			sopPK.setProductid(25);
			sopPK.setSpecialofferid(5);
			specialOfferProduct.setRowguid(5);
			specialOfferProduct.setId(sopPK);
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			specialOfferProduct.setModifieddate(timestamp);
			
			Mockito.when(restTemplate.postForEntity(SOP_URL, specialOfferProduct, Specialofferproduct.class))
			.thenReturn(new ResponseEntity<Specialofferproduct>(specialOfferProduct, HttpStatus.OK));
			businessDelegate.specialofferproductSave(specialOfferProduct);

			Mockito.doNothing().when(restTemplate).put(SOP_URL + specialOfferProduct.getId(), specialOfferProduct, Specialofferproduct.class);
			businessDelegate.specialofferproductDelete(specialOfferProduct);

			when(restTemplate.getForObject(SOP_URL + specialOfferProduct.getId(), null))
			.thenReturn(new ResponseEntity(null, HttpStatus.OK).getBody());
			
			assertNull(businessDelegate.specialofferproductFindById(specialOfferProduct.getId()));
			
		}
		
		//SalesOrderDetail
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void deleteSalesOrderDetailTest() {
			Salesorderdetail salesOrderDetail = new Salesorderdetail();
			salesOrderDetail.setId(52);
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			salesOrderDetail.setModifieddate(timestamp);
			BigDecimal price = new BigDecimal(100);
			salesOrderDetail.setUnitprice(price);
			

			Mockito.when(restTemplate.postForEntity(SOD_URL, salesOrderDetail, Salesorderdetail.class))
			.thenReturn(new ResponseEntity<Salesorderdetail>(salesOrderDetail, HttpStatus.OK));
			businessDelegate.salesOrderDetailSave(salesOrderDetail);

			Mockito.doNothing().when(restTemplate).put(SOD_URL + salesOrderDetail.getId(), salesOrderDetail, Salesorderdetail.class);
			businessDelegate.salesOrderDetailDelete(salesOrderDetail);

			when(restTemplate.getForObject(SOD_URL + salesOrderDetail.getId(), null))
			.thenReturn(new ResponseEntity(null, HttpStatus.OK).getBody());
			
			assertNull(businessDelegate.salesOrderDetailFindById(salesOrderDetail.getId()));
			
		}	
	
	}
}
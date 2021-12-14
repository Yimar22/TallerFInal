package com.tamayo.PruebasIntegracion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tamayo.Application.Taller1YimarTamayoApplication;
import com.tamayo.back.daos.ProductDao;
import com.tamayo.back.model.Product;
import com.tamayo.back.repositories.ProductModelRepository;
import com.tamayo.back.repositories.ProductRepository;
import com.tamayo.back.repositories.ProductSubCategoryRepository;
import com.tamayo.back.repositories.UnitMeasureRepository;
import com.tamayo.back.services.ProductService;
import com.tamayo.back.services.ProductServiceImp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Taller1YimarTamayoApplication.class)

class ProductTest {

	@Autowired
	private ProductDao pDao;
	@Autowired
	private ProductRepository productrepo;
	@Autowired
	private ProductSubCategoryRepository productSubcategoryRepository;
	@Autowired
	private ProductService productServ;
	@Autowired
	private UnitMeasureRepository unitMeasureRepository;
	@Autowired
	private ProductModelRepository productModelRepository;
	
	@Autowired
	public ProductTest() {
		this.productServ = new ProductServiceImp(pDao, productSubcategoryRepository, productrepo, unitMeasureRepository, productModelRepository);
	}
	
	@BeforeAll
	public static void setUp() {	
		System.out.println("-----> SETUP <-----");
	}
	
	@Nested
	@DisplayName("Save Cases")
	class Save{
		@SuppressWarnings("unlikely-arg-type")
		@Test
		@DisplayName("Save_product_valid_test_1")
		public void testSaveProductProperly1() {

			Product prod = new Product();
			prod.setProductid(13);
			productrepo.save(prod);

			Product newProd = new Product();
			assertTrue(newProd.equals(productrepo.findById(newProd.getProductid())),
					"The entity could not be saved");
		}

		@SuppressWarnings("unlikely-arg-type")
		@Test
		@DisplayName("Save_autotran_valid_test_2")
		public void testSaveAutoTransitionProperly2() {
			
			Product prod = new Product();
			prod.setProductid(73);
			productrepo.save(prod);

			Product newProd = new Product();
			assertTrue(newProd.equals(productrepo.findById(newProd.getProductid())),
					"The entity could not be saved");
		}



		@SuppressWarnings("unlikely-arg-type")
		@Test
		@DisplayName("Save_autotran_invalid_test_1")
		public void testSaveAutoTransitionImproperly1() {

			Product prod = new Product();
			prod.setProductid(13);
			productrepo.save(prod);

			Product newProd = new Product();
			assertFalse(newProd.equals(productrepo.findById(newProd.getProductid())),
					"The entity could not be saved");
			
		}

		@SuppressWarnings("unlikely-arg-type")
		@Test
		@DisplayName("Save_autotran_invalid_test_2")
		public void testSaveAutoTransitionImproperly2() {
			Product prod = new Product();
			prod.setProductid(23);
			productrepo.save(prod);

			Product newProd = new Product();
			assertFalse(newProd.equals(productrepo.findById(newProd.getProductid())),
					"The entity could not be saved");
			
		}

	
	}
	
	@Nested
	@DisplayName("Edit cases")
	class Edit{
		@SuppressWarnings("unlikely-arg-type")
		@Test
		@DisplayName("Edit_autotran_valid_test")
		public void testEditAutoTransitionProperly(){
			Product prod = new Product();
			prod.setProductid(13);
			productrepo.save(prod);
	
			Product newProd = new Product();
			newProd = productServ.saveProduct(prod);
			newProd.setProductid(52);
			
			assertTrue(productrepo.equals(newProd.getProductid()),
					"Could not find the entity to edit");	
		}
		
		@SuppressWarnings("unlikely-arg-type")
		@Test
		@DisplayName("Edit_autotran_invalid_test1")
		public void testEditAutoTransitionImproperly1() {
			Product prod = new Product();
			prod.setProductid(31);
			productrepo.save(prod);
	
			Product newProd = new Product();
			newProd = productServ.saveProduct(prod);
			newProd.setProductid(25);
			
			assertFalse(productrepo.equals(newProd.getProductid()),
					"Could not find the entity to edit");	
		}
	
		
		
	}
	
	@AfterAll
	public static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}

}

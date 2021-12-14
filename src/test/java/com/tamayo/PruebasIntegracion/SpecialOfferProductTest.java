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
import com.tamayo.back.daos.SpecialofferproductDao;
import com.tamayo.back.model.Specialofferproduct;
import com.tamayo.back.model.SpecialofferproductPK;
import com.tamayo.back.repositories.SpecialOfferProductRepository;
import com.tamayo.back.services.SpecialOfferProductService;
import com.tamayo.back.services.SpecialOfferProductServiceImp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Taller1YimarTamayoApplication.class)
class SpecialOfferProductTest {

	@Autowired
	private SpecialofferproductDao sopDao;
	@Autowired
	private SpecialOfferProductRepository sopRepo;
	@Autowired
	private SpecialOfferProductService sopServ;
	@Autowired
	private SpecialofferproductPK sopPk;
	
	
	@Autowired
	public SpecialOfferProductTest() {
		this.sopServ = new SpecialOfferProductServiceImp(sopDao, sopRepo);
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

			Specialofferproduct sop = new Specialofferproduct();
			sopPk.setProductid(13);
			sop.setId(sopPk);
			sopRepo.save(sop);

			Specialofferproduct newSop = new Specialofferproduct();
			assertTrue(newSop.equals(sopRepo.findById(newSop.getId())),
					"The entity could not be saved");
		}

		@SuppressWarnings("unlikely-arg-type")
		@Test
		@DisplayName("Save_autotran_valid_test_2")
		public void testSaveAutoTransitionProperly2() {
			Specialofferproduct sop = new Specialofferproduct();
			sopPk.setProductid(73);
			sop.setId(sopPk);
			sopRepo.save(sop);

			Specialofferproduct newSop = new Specialofferproduct();
			assertTrue(newSop.equals(sopRepo.findById(newSop.getId())),
					"The entity could not be saved");
		}



		@SuppressWarnings("unlikely-arg-type")
		@Test
		@DisplayName("Save_autotran_invalid_test_1")
		public void testSaveAutoTransitionImproperly1() {

			Specialofferproduct sop = new Specialofferproduct();
			sopPk.setProductid(13);
			sop.setId(sopPk);
			sopRepo.save(sop);

			Specialofferproduct newSop = new Specialofferproduct();
			
			assertFalse(newSop.equals(sopRepo.findById(newSop.getId())),
					"The entity could not be saved");
			
		}

		@SuppressWarnings("unlikely-arg-type")
		@Test
		@DisplayName("Save_autotran_invalid_test_2")
		public void testSaveAutoTransitionImproperly2() {
			Specialofferproduct sop = new Specialofferproduct();
			sopPk.setProductid(223);
			sop.setId(sopPk);
			sopRepo.save(sop);

			Specialofferproduct newSop = new Specialofferproduct();
			
			assertFalse(newSop.equals(sopRepo.findById(newSop.getId())),
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
			Specialofferproduct sop = new Specialofferproduct();
			sopPk.setProductid(13);
			sop.setId(sopPk);
			sopRepo.save(sop);

			Specialofferproduct newSop = new Specialofferproduct();
			newSop = sopServ.save(sop);
			sopPk.setProductid(52);
			newSop.setId(sopPk);
			
			assertTrue(sopRepo.equals(newSop.getId()),
					"Could not find the entity to edit");	
		}
		
		@SuppressWarnings("unlikely-arg-type")
		@Test
		@DisplayName("Edit_autotran_invalid_test1")
		public void testEditAutoTransitionImproperly1() {
			Specialofferproduct sop = new Specialofferproduct();
			sopPk.setProductid(130);
			sop.setId(sopPk);
			sopRepo.save(sop);

			Specialofferproduct newSop = new Specialofferproduct();
			newSop = sopServ.save(sop);
			sopPk.setProductid(1);
			newSop.setId(sopPk);
			
			assertFalse(sopRepo.equals(newSop.getId()),
					"Could not find the entity to edit");	
		}
	
		
		
	}
	
	@AfterAll
	public static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}

}

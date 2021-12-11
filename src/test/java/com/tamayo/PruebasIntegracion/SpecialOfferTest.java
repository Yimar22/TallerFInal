package com.tamayo.PruebasIntegracion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tamayo.TallerPruebas.Taller1YimarTamayoApplication;
import com.tamayo.back.daos.SpecialOfferDao;
import com.tamayo.back.daos.SpecialofferproductDao;
import com.tamayo.back.model.SpecialofferproductPK;
import com.tamayo.back.repositories.SpecialOfferProductRepository;
import com.tamayo.back.repositories.SpecialOfferRepository;
import com.tamayo.back.services.SpecialOfferProductService;
import com.tamayo.back.services.SpecialOfferProductServiceImp;
import com.tamayo.back.services.SpecialOfferService;
import com.tamayo.back.services.SpecialOfferServiceImp;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= Taller1YimarTamayoApplication.class)
class SpecialOfferTest {

	@Autowired
	private SpecialOfferDao soDao;
	@Autowired
	private SpecialOfferRepository soRepo;
	@Autowired
	private SpecialOfferService soServ;

	
	
	@Autowired
	public SpecialOfferTest() {
		this.soServ = new SpecialOfferServiceImp(soDao, soRepo);
	}
	
	
	@BeforeAll
	public static void setUp() {	
		System.out.println("-----> SETUP <-----");
	}
	
	@AfterAll
	public static void afterTest() {
		System.out.println("-----> DESTROY <-----");
	}
	
}

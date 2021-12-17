package com.tamayo.Application;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.tamayo.back.model.User;
import com.tamayo.back.model.UserType;
import com.tamayo.back.services.ProductSubCategoryService;
import com.tamayo.back.services.UnitMeasureService;
import com.tamayo.back.services.UserService;
import com.tamayo.back.model.Productcategory;
import com.tamayo.back.model.Productsubcategory;
import com.tamayo.back.model.Unitmeasure;


@SpringBootApplication
@ComponentScan(basePackages = "com.tamayo")
public class TallerFinalApplication {
	
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TallerFinalApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner dummy(UserService userService, ProductSubCategoryService productSubcategoryService, UnitMeasureService unitMeasureService) {
		return (args) -> {
				User u = new User(1,null, "YimarT","0000", UserType.administrador, null);
				userService.save(u);
				User v = new User(2,null,"ausar","00000", UserType.operador,null);
				userService.save(v);
				
				setDefaultSubcategories(productSubcategoryService);
				setDefaultUnitMeasures1(unitMeasureService);
		};
	}

	private void setDefaultUnitMeasures1(UnitMeasureService unitMeasureService) {
		Unitmeasure umT1 = new Unitmeasure();
		umT1.setUnitmeasurecode("cm");
		umT1.setName("cm");
		unitMeasureService.saveUnitMeasure1(umT1);
		Unitmeasure umT2 = new Unitmeasure();
		umT2.setUnitmeasurecode("mm");
		umT2.setName("mm");
		unitMeasureService.saveUnitMeasure1(umT2);
	}

	private void setDefaultSubcategories(ProductSubCategoryService productSubcategoryService) {
		Productsubcategory psc1 = new Productsubcategory();
		psc1.setProductsubcategoryid(1);
		Productcategory pc1 = new Productcategory();
		pc1.setProductcategoryid(1);
		pc1.setName("Verdura");
		psc1.setProductcategory(pc1);
		psc1.setName("Cebolla");
		productSubcategoryService.saveProductSubcategory(psc1);
		Productsubcategory psc2 = new Productsubcategory();
		psc2.setProductsubcategoryid(2);
		Productcategory pc2 = new Productcategory();
		pc2.setProductcategoryid(2);
		pc2.setName("Fruta");
		psc2.setProductcategory(pc2);
		psc2.setName("Manzana");
		productSubcategoryService.saveProductSubcategory(psc2);
	}


}

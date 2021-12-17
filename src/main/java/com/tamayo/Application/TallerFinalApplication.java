package com.tamayo.Application;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.tamayo.back.model.User;
import com.tamayo.back.model.UserType;
import com.tamayo.back.model.Workorder;
import com.tamayo.back.model.Workorderrouting;
import com.tamayo.back.services.LocationServiceImpl;
import com.tamayo.back.services.ProductServiceImp;
import com.tamayo.back.services.ProductSubcategoryServiceImpl;
import com.tamayo.back.services.UnitMeasureServiceImpl;
import com.tamayo.back.services.UserServiceImp;
import com.tamayo.back.services.WorkorderServiceImpl;
import com.tamayo.back.services.WorkorderroutingServiceImpl;
import com.tamayo.back.model.Location;
import com.tamayo.back.model.Product;
import com.tamayo.back.model.Productcategory;
import com.tamayo.back.model.Productsubcategory;
import com.tamayo.back.model.Unitmeasure;


@SpringBootApplication
@ComponentScan(basePackages = "com.tamayo")
public class TallerFinalApplication {
	
	private static ProductSubcategoryServiceImpl pss;
	private static int ps1;
	private static int ps2;
	private static UnitMeasureServiceImpl ums;
	private static String um1;
	private static String um2;
	private static ProductServiceImp ps;
	private static int p1;
	private static int p2;
	private static WorkorderServiceImpl wos;
	private static int wo1;
	private static int wo2;
	private static LocationServiceImpl ls;
	private static int l1;
	private static int l2;
	private static WorkorderroutingServiceImpl wors;
	private static int wor1;
	private static int wor2;
	private static UserServiceImp us;
	
	
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TallerFinalApplication.class, args);
		
		pss = context.getBean(ProductSubcategoryServiceImpl.class);
		ums = context.getBean(UnitMeasureServiceImpl.class);
		ps = context.getBean(ProductServiceImp.class);
		wos = context.getBean(WorkorderServiceImpl.class);
		ls = context.getBean(LocationServiceImpl.class);
		wors = context.getBean(WorkorderroutingServiceImpl.class);
		us = context.getBean(UserServiceImp.class);
		
		
		setDefaultSubcategories(context);
		setDefaultUnitmeasures(context);
		setDefaultProducts(context);
		setDefaultWorkorders(context);
		setDefaultLocations(context);
		setDefaultWorkorderroutings(context);
		setUsers(context);
	}
	
	private static void setDefaultLocations(ConfigurableApplicationContext context) {
		Location l = new Location();
		l.setName("D1");
		l.setModifieddate(new Timestamp(System.currentTimeMillis()));
		l1 = ls.saveLocation(l).getLocationid();
		
		l = new Location();
		l.setName("D2");
		l.setModifieddate(new Timestamp(System.currentTimeMillis()));
		l2 = ls.saveLocation(l).getLocationid();
		}

	private static void setDefaultWorkorderroutings(ConfigurableApplicationContext context) {
		Workorderrouting wor = new Workorderrouting();
		wor.setActualcost(new BigDecimal("123.5"));
		wor.setActualenddate(new Timestamp(System.currentTimeMillis()+5L* 24 * 60 * 60 * 1000));
		wor.setActualresourcehrs(new BigDecimal("3"));
		wor.setActualstartdate(new Timestamp(System.currentTimeMillis()+1L* 24 * 60 * 60 * 1000));
		wor.setModifieddate(new Timestamp(System.currentTimeMillis()));
		wor.setPlannedcost(new BigDecimal("100.4"));
		wor.setScheduledenddate(new Timestamp(System.currentTimeMillis()+1L* 24 * 60 * 60 * 1000));
		wor.setScheduledstartdate(new Timestamp(System.currentTimeMillis()+4L* 24 * 60 * 60 * 1000));
		wor.setLocation(ls.findById(l1).get());
		wor.setWorkorder(wos.findById(wo1).get());
		wor1 = wors.saveWorkorderrouting(wor).getWorkorderroutingid();
		
		wor = new Workorderrouting();
		wor.setActualcost(new BigDecimal("223.5"));
		wor.setActualenddate(new Timestamp(System.currentTimeMillis()+10L* 24 * 60 * 60 * 1000));
		wor.setActualresourcehrs(new BigDecimal("5"));
		wor.setActualstartdate(new Timestamp(System.currentTimeMillis()+2L* 24 * 60 * 60 * 1000));
		wor.setModifieddate(new Timestamp(System.currentTimeMillis()));
		wor.setPlannedcost(new BigDecimal("1000.4"));
		wor.setScheduledenddate(new Timestamp(System.currentTimeMillis()+1L* 24 * 60 * 60 * 1000));
		wor.setScheduledstartdate(new Timestamp(System.currentTimeMillis()+100L* 24 * 60 * 60 * 1000));
		wor.setLocation(ls.findById(l2).get());
		wor.setWorkorder(wos.findById(wo2).get());
		wor2 = wors.saveWorkorderrouting(wor).getWorkorderroutingid();
		
		setDefaultRoutingsForDeafultWorkorders();
	}

	private static void setDefaultRoutingsForDeafultWorkorders() {
		wos.findById(wo1).get().getWorkorderroutings().add(wors.findById(wor1).get());
		wos.findById(wo1).get().getWorkorderroutings().add(wors.findById(wor2).get());
		wos.findById(wo2).get().getWorkorderroutings().add(wors.findById(wor2).get());
	}

	private static void setDefaultWorkorders(ConfigurableApplicationContext context) {
		Workorder wo = new Workorder();
		wo.setProduct(ps.findById(p1));
		wo.setOrderqty(10);
		wo.setScrappedqty(5);
		wo.setStartdate(new Timestamp(System.currentTimeMillis()));
		wo.setEnddate(new Timestamp(System.currentTimeMillis()+2L* 24 * 60 * 60 * 1000));
		wo.setDuedate(new Timestamp(System.currentTimeMillis()+3L* 24 * 60 * 60 * 1000));
		wo.setModifieddate(new Timestamp(System.currentTimeMillis()));
		wo.setWorkorderroutings(new ArrayList<Workorderrouting>());
		wo1=wos.saveWorkorder(wo).getWorkorderid();
		
		wo = new Workorder();
		wo.setProduct(ps.findById(p2));
		wo.setOrderqty(20);
		wo.setScrappedqty(3);
		wo.setStartdate(new Timestamp(System.currentTimeMillis()+1L* 24 * 60 * 60 * 1000));
		wo.setEnddate(new Timestamp(System.currentTimeMillis()+10L* 24 * 60 * 60 * 1000));
		wo.setDuedate(new Timestamp(System.currentTimeMillis()+30L* 24 * 60 * 60 * 1000));
		wo.setModifieddate(new Timestamp(System.currentTimeMillis()));
		wo.setWorkorderroutings(new ArrayList<Workorderrouting>());
		wo2=wos.saveWorkorder(wo).getWorkorderid();
	}

	private static void setUsers(ConfigurableApplicationContext context) {
		User u = new User(1,null, "YimarT","0000", UserType.administrador, null);
		us.save(u);
		User v = new User(2,null,"ausar","00000", UserType.operador,null);
		us.save(v);
		
	}

	private static void setDefaultProducts(ConfigurableApplicationContext context) {
		Product p = new Product();
		p.setName("Cebolla cabezona");
		p.setProductnumber("123");
		p.setSellstartdate(new Timestamp(System.currentTimeMillis()));
		p.setSellenddate(new Timestamp(System.currentTimeMillis()+1L* 24 * 60 * 60 * 1000));
		p.setDaystomanufacture(1);
		p.setModifieddate(new Timestamp(System.currentTimeMillis()));
		p.setUnitmeasure1(ums.findById1(um1).get());
		p.setProductsubcategory(pss.findById(ps1).get());
		p1 = ps.saveProduct(p).getProductid();
		
		p = new Product();
		p.setName("Manzana roja");
		p.setProductnumber("321");
		p.setSellstartdate(new Timestamp(System.currentTimeMillis()));
		p.setSellenddate(new Timestamp(System.currentTimeMillis()+10L* 24 * 60 * 60 * 1000));
		p.setDaystomanufacture(5);
		p.setModifieddate(new Timestamp(System.currentTimeMillis()));
		p.setUnitmeasure1(ums.findById1(um2).get());
		p.setProductsubcategory(pss.findById(ps2).get());
		p2 = ps.saveProduct(p).getProductid();
	}

	private static void setDefaultUnitmeasures(ConfigurableApplicationContext context) {
		Unitmeasure umT = new Unitmeasure();
		umT.setUnitmeasurecode("cm");
		umT.setName("cm");
		umT.setModifieddate(new Timestamp(System.currentTimeMillis()));
		um1 = ums.saveUnitMeasure1(umT).getUnitmeasurecode();
		
		umT = new Unitmeasure();
		umT.setUnitmeasurecode("mm");
		umT.setName("mm");
		umT.setModifieddate(new Timestamp(System.currentTimeMillis()));
		um2 = ums.saveUnitMeasure1(umT).getUnitmeasurecode(); 
	}

	private static void setDefaultSubcategories(ConfigurableApplicationContext context) {
		
		Productsubcategory psc = new Productsubcategory();
		Productcategory pc = new Productcategory();
		pc.setProductcategoryid(1);
		pc.setName("Verdura");
		psc.setProductcategory(pc);
		psc.setName("Cebolla");
		psc.setModifieddate(new Timestamp(System.currentTimeMillis()));
		ps1 = pss.saveProductSubcategory(psc).getProductsubcategoryid();
		psc = new Productsubcategory();
		pc = new Productcategory();
		pc.setProductcategoryid(2);
		pc.setName("Fruta");
		psc.setProductcategory(pc);
		psc.setName("Manzana");
		psc.setModifieddate(new Timestamp(System.currentTimeMillis()));
		ps2 = pss.saveProductSubcategory(psc).getProductsubcategoryid();
		
	}




}

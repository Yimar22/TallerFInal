package com.tamayo.back.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tamayo.back.model.PurchaseorderdetailPK;
import com.tamayo.back.model.Salesorderdetail;
import com.tamayo.back.model.SalesorderdetailPK;
@Repository
public interface SalesOrderDetailRepository extends CrudRepository<Salesorderdetail, SalesorderdetailPK>{

	Optional<Salesorderdetail> findById(Integer id);

	boolean existsById(Integer salesorderdetailid);

	Optional<Salesorderdetail> findById(PurchaseorderdetailPK id);


}

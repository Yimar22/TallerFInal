package com.tamayo.back.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.tamayo.back.model.PurchaseorderdetailPK;
import com.tamayo.back.model.Salesorderdetail;
import com.tamayo.back.model.SalesorderdetailPK;
@RepositoryRestResource
public interface SalesOrderDetailRepository extends CrudRepository<Salesorderdetail, Integer>{

	Optional<Salesorderdetail> findById(Integer id);

	boolean existsById(Integer salesorderdetailid);
}

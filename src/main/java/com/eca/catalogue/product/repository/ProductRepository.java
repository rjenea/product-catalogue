package com.eca.catalogue.product.repository;

import com.eca.catalogue.product.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, String> {

    List<Product> findByLocationIdOrLocationIdIsNull(String locationId);

}

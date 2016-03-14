package com.sky.catalogue.product.repository;

import com.sky.catalogue.product.model.Product;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, String> {

    List<Product> findByLocationIdOrLocationIdIsNull(String locationId);

}

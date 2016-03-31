package com.eca.catalogue.facade;


import com.eca.catalogue.product.vo.ProductVO;

import java.util.List;

/**
 * This facade describes all available products and services.
 */
public interface CatalogueService {

    List<ProductVO> getProducts(String locationId);

    ProductVO save(ProductVO productVO);

}

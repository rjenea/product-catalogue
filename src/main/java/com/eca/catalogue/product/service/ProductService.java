package com.eca.catalogue.product.service;

import com.eca.catalogue.product.vo.ProductVO;

import java.util.List;

public interface ProductService {

    List<ProductVO> getProducts(String locationId);

    ProductVO save(ProductVO productVO);
}

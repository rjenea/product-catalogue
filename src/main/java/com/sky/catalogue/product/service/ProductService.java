package com.sky.catalogue.product.service;

import com.sky.catalogue.product.vo.ProductVO;

import java.util.List;

public interface ProductService {

    List<ProductVO> getProducts(String locationId);

    ProductVO save(ProductVO productVO);
}

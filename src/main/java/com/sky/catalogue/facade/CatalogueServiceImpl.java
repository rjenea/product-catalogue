package com.sky.catalogue.facade;

import com.sky.catalogue.product.service.ProductService;
import com.sky.catalogue.product.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class CatalogueServiceImpl implements CatalogueService {

    private final ProductService productService;

    @Autowired
    public CatalogueServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<ProductVO> getProducts(String locationId) {
        return productService.getProducts(locationId);
    }

    @Override
    public ProductVO save(ProductVO productVO) {
        return productService.save(productVO);
    }
}

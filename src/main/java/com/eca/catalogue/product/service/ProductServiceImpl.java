package com.eca.catalogue.product.service;

import com.google.common.collect.ImmutableList;
import com.eca.catalogue.product.model.Product;
import com.eca.catalogue.product.repository.ProductRepository;
import com.eca.catalogue.product.util.TransformerUtil;
import com.eca.catalogue.product.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.eca.catalogue.product.util.TransformerUtil.transform;
import static java.util.stream.Collectors.toList;

@Service
public final class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductVO save(ProductVO productVO) {
        Product product = transform(productVO);
        product = productRepository.save(product);
        return transform(product);
    }

    @Override
    public List<ProductVO> getProducts(String locationId) {
        List<Product> products = productRepository.findByLocationIdOrLocationIdIsNull(locationId);
        List<ProductVO> productVO = products.stream().map(TransformerUtil::transform).collect(toList());
        return ImmutableList.copyOf(productVO);
    }
}

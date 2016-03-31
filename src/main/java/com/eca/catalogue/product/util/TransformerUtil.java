package com.eca.catalogue.product.util;


import com.eca.catalogue.product.model.Product;
import com.eca.catalogue.product.vo.ProductVO;

import static com.google.common.base.Preconditions.checkNotNull;

public final class TransformerUtil {

    private TransformerUtil() {
        throw new UnsupportedOperationException();
    }

    public static ProductVO transform(Product product) {
        checkNotNull(product,"Unexpected null product.");
        return ProductVO.builder()
                .setCategory(product.getCategory())
                .setName(product.getName())
                .setLocationId(product.getLocationId())
                .build();
    }

    public static Product transform(ProductVO productVO) {
        checkNotNull(productVO,"Unexpected null product.");
        return Product.builder()
                .setName(productVO.getName())
                .setCategory(productVO.getCategory())
                .setLocationId(productVO.getLocationId().orNull())
                .build();
    }
}

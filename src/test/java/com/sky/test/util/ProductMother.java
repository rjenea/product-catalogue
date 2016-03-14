package com.sky.test.util;

import com.sky.catalogue.product.model.Product;
import com.sky.catalogue.product.vo.ProductVO;

public final class ProductMother {

    private ProductMother() {
    }

    public static Product produceProductArsenalTv() {
        return Product.builder().setCategory("Sport").setName("ArsenalTV").setLocationId("London").build();
    }

    public static Product produceProductLiverpoolTv() {
        return Product.builder().setCategory("Sport").setName("LiverpoolTV").setLocationId("Liverpool").build();
    }

    public static Product produceProductSkyNews() {
        return Product.builder().setCategory("News").setName("SkyNews").build();
    }

    public static ProductVO produceProductVOLiverpoolTv() {
        return ProductVO.builder().setCategory("Sport").setName("LiverpoolTV").setLocationId("Liverpool").build();
    }

    public static ProductVO produceProductVOSkySportNews() {
        return ProductVO.builder().setCategory("News").setName("SkySportNews").build();
    }

}

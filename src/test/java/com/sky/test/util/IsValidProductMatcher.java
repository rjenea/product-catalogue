package com.sky.test.util;

import com.sky.catalogue.product.model.Product;
import com.sky.catalogue.product.vo.ProductVO;
import org.hamcrest.Matcher;

import static com.google.common.base.Optional.fromNullable;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public final class IsValidProductMatcher {

    private IsValidProductMatcher() {
        throw new UnsupportedOperationException();
    }

    public static <T> Matcher<T> matchesWith(ProductVO productVO) {
        checkNotNull(productVO);
        return allOf(
                hasProperty("category", equalTo(productVO.getCategory())),
                hasProperty("name", equalTo(productVO.getName())),
                hasProperty("locationId", locationMatcher(productVO.getLocationId().orNull()))
        );
    }

    public static <T> Matcher<T> matchesWith(Product product) {
        checkNotNull(product);
        return allOf(
                hasProperty("category", equalTo(product.getCategory())),
                hasProperty("name", equalTo(product.getName())),
                hasProperty("locationId", locationMatcher(product.getLocationId()))
        );
    }

    private static Matcher locationMatcher(String locationId) {
        return anyOf(equalTo(locationId), equalTo(fromNullable(locationId)));
    }
}

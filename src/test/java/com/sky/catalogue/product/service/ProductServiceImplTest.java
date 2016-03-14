package com.sky.catalogue.product.service;

import autovalue.shaded.com.google.common.common.collect.ImmutableCollection;
import autovalue.shaded.com.google.common.common.collect.Lists;
import com.sky.catalogue.product.model.Product;
import com.sky.catalogue.product.repository.ProductRepository;
import com.sky.catalogue.product.util.TransformerUtil;
import com.sky.catalogue.product.vo.ProductVO;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.sky.catalogue.product.util.TransformerUtil.transform;
import static com.sky.test.util.IsValidProductMatcher.matchesWith;
import static com.sky.test.util.ProductMother.produceProductArsenalTv;
import static com.sky.test.util.ProductMother.produceProductSkyNews;
import static com.sky.test.util.ProductMother.produceProductVOSkySportNews;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void shouldThrowNullPointerException_WhenSaveNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Unexpected null product.");
        productService.save(null);
    }

    @Test
    public void shouldPersistValidProduct() {
        ProductVO toBePersisted = produceProductVOSkySportNews();
        when(productRepository.save(any(Product.class))).thenReturn(transform(toBePersisted));
        ProductVO persistedProduct = productService.save(toBePersisted);
        assertEquals(persistedProduct, toBePersisted);
    }

    @Test
    public void getProducts_WhenLocationIdIsNull_ShouldReturnEmptyCollection() {
        when(productRepository.findByLocationIdOrLocationIdIsNull(null)).thenReturn(Lists.newArrayList());
        List<ProductVO> products = productService.getProducts(null);
        assertThat(products, empty());
        verify(productRepository, times(1)).findByLocationIdOrLocationIdIsNull(null);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getProducts_WhenLocationIdIsKnown_ShouldReturnTransformedVO() {
        when(productRepository.findByLocationIdOrLocationIdIsNull("London")).thenReturn(Lists.newArrayList(produceProductArsenalTv(), produceProductSkyNews()));
        List<ProductVO> products = productService.getProducts("London");
        assertThat(products, prepareLondonMatcher());
        verify(productRepository, times(1)).findByLocationIdOrLocationIdIsNull("London");
    }

    @SuppressWarnings("unchecked")
    @Test(expected = UnsupportedOperationException.class)
    public void getProducts_ShouldBeImmutable() {
        when(productRepository.findByLocationIdOrLocationIdIsNull("London")).thenReturn(Lists.newArrayList(produceProductArsenalTv(), produceProductSkyNews()));
        List products = productService.getProducts("London");
        products.add(produceProductArsenalTv());
    }

    private Matcher prepareLondonMatcher() {
        Matcher arsenalMatcher = matchesWith(produceProductArsenalTv());
        Matcher skyNewsMatcher = matchesWith(produceProductSkyNews());
        return containsInAnyOrder(arsenalMatcher, skyNewsMatcher);
    }

}
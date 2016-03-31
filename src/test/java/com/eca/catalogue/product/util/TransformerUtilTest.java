package com.eca.catalogue.product.util;

import com.eca.catalogue.product.model.Product;
import com.eca.catalogue.product.vo.ProductVO;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.eca.test.util.IsValidProductMatcher.matchesWith;
import static com.eca.test.util.IsValidProductMatcher.matchesWith;
import static com.eca.catalogue.product.util.TransformerUtil.transform;
import static com.eca.test.util.ProductMother.produceProductArsenalTv;
import static com.eca.test.util.ProductMother.produceProductVOLiverpoolTv;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertThat;

public class TransformerUtilTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldNotAllowInstantiation() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        thrown.expect(hasProperty("targetException", CoreMatchers.instanceOf(UnsupportedOperationException.class)));

        Constructor constructor = TransformerUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
    }

    @Test
    public void transform_FromProductToProductVO_WhenPassingNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Unexpected null product.");

        Product from = null;
        transform(from);
    }

    @Test
    public void transform_FromProductToProductVO()  {
        Product from = produceProductArsenalTv();
        ProductVO to = transform(from);
        assertThat(to, matchesWith(from));
    }

    @Test
    public void transform_FromProductVOToProduct_WhenPassingNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Unexpected null product.");

        ProductVO from = null;
        transform(from);
    }

    @Test
    public void transform_FromProductVOToProduct()  {
        ProductVO from = produceProductVOLiverpoolTv();
        Product to = transform(from);
        assertThat(to, matchesWith(from));
    }
}
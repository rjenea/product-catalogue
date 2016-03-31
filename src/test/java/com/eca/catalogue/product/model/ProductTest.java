package com.eca.catalogue.product.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.eca.test.util.ProductMother.produceProductArsenalTv;
import static com.eca.test.util.ProductMother.produceProductSkyNews;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;
import static org.mutabilitydetector.unittesting.MutabilityAssert.assertInstancesOf;
import static org.mutabilitydetector.unittesting.MutabilityMatchers.areImmutable;

public class ProductTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldHaveProperties() {
        Product product = produceProductArsenalTv();
        assertThat(product, hasProperty("category", equalTo("Sport")));
        assertThat(product, hasProperty("name", equalTo("ArsenalTV")));
        assertThat(product, hasProperty("locationId", equalTo("London")));
    }

    @Test
    public void shouldHaveBuilder() {
        assertNotNull(Product.builder());
        assertThat(Product.builder().getClass(), sameInstance(Product.Builder.class));
    }

    @Test
    public void shouldProvideCategory() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Category is mandatory");
        Product.builder().setName("A").build();
    }

    @Test
    public void shouldProvideName() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Name is mandatory");
        Product.builder().setCategory("A").build();
    }

    @Test
    public void shouldAllowCreationWithoutLocationId() {
        Product product = produceProductSkyNews();
        assertNull(product.getLocationId());
    }

    @Test
    public void sameInstanceShouldBeEqual() {
        Product product = produceProductArsenalTv();
        assertTrue(product.equals(product));
    }

    @Test
    public void couldNotBeEqualWithNull() {
        Product product = produceProductArsenalTv();
        assertFalse(product.equals(null));
    }

    @Test
    public void couldNotBeEqualWithIncompatibleType() {
        Product product = produceProductArsenalTv();
        assertFalse(product.equals("ANOTHER TYPE"));
    }

    @Test
    public void shouldBeEqualWithSameValueSet() {
        Product productA = produceProductArsenalTv();
        Product productB = produceProductArsenalTv();
        assertTrue(productA.equals(productB));
    }

    @Test
    public void hashcodeShouldBeConsistent() {
        Product product = produceProductArsenalTv();
        int initialHashcode = product.hashCode();
        assertEquals(initialHashcode, product.hashCode());
        assertEquals(initialHashcode, product.hashCode());
    }

    @Test
    public void shouldHaveSameHashcodeForEqualObjects() {
        int hashProductA = produceProductArsenalTv().hashCode();
        int hashProductB = produceProductArsenalTv().hashCode();
        assertEquals(hashProductA, hashProductB);
    }

    @Test
    public void shouldHaveDifferentHashcodeForNonEqualObjects() {
        int hashProductA = produceProductArsenalTv().hashCode();
        int hashProductB = produceProductSkyNews().hashCode();
        assertTrue(hashProductA != hashProductB);
    }

    @Test
    public void shouldBeImmutable() {
        assertInstancesOf(produceProductSkyNews().getClass(), areImmutable());
    }
}
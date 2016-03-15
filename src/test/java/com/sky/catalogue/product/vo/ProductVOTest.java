package com.sky.catalogue.product.vo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.sky.test.util.Serializer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.google.common.base.Optional.of;
import static com.sky.test.util.ProductMother.produceProductVOLiverpoolTv;
import static com.sky.test.util.ProductMother.produceProductVOSkySportNews;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;
import static org.mutabilitydetector.unittesting.AllowedReason.provided;
import static org.mutabilitydetector.unittesting.MutabilityAssert.assertInstancesOf;
import static org.mutabilitydetector.unittesting.MutabilityMatchers.areImmutable;

public class ProductVOTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldHaveProperties() {
        ProductVO product = produceProductVOLiverpoolTv();
        assertThat(product, hasProperty("category", equalTo("Sport")));
        assertThat(product, hasProperty("name", equalTo("LiverpoolTV")));
        assertThat(product, hasProperty("locationId", equalTo(of("Liverpool"))));
    }

    @Test
    public void shouldHaveGetters() {
        ProductVO product = produceProductVOLiverpoolTv();
        assertEquals(product.getCategory(), "Sport");
        assertEquals(product.getName(), "LiverpoolTV");
        assertEquals(product.getLocationId().get(), "Liverpool");
    }

    @Test
    public void shouldHaveBuilder() {
        ProductVO.Builder builder = ProductVO.builder();
        assertNotNull(builder);
        assertThat(builder.getClass(), sameInstance(AutoValue_ProductVO.Builder.class));
    }

    @Test
    public void shouldProvideCategory() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Missing required properties: category");
        ProductVO.builder().setName("A").build();
    }

    @Test
    public void shouldProvideName() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Missing required properties: name");
        ProductVO.builder().setCategory("A").build();
    }

    @Test
    public void shouldAllowCreationWithoutLocationId() {
        ProductVO product = produceProductVOSkySportNews();
        assertFalse(product.getLocationId().isPresent());
    }

    @Test
    public void sameInstanceShouldBeEqual() {
        ProductVO product = produceProductVOLiverpoolTv();
        assertTrue(product.equals(product));
    }

    @Test
    public void couldNotBeEqualWithNull() {
        ProductVO product = produceProductVOLiverpoolTv();
        assertFalse(product.equals(null));
    }

    @Test
    public void couldNotBeEqualWithIncompatibleType() {
        ProductVO product = produceProductVOLiverpoolTv();
        assertFalse(product.equals("ANOTHER TYPE"));
    }

    @Test
    public void shouldBeEqualWithSameValueSet() {
        ProductVO productA = produceProductVOLiverpoolTv();
        ProductVO productB = produceProductVOLiverpoolTv();
        assertTrue(productA.equals(productB));
    }

    @Test
    public void hashcodeShouldBeConsistent() {
        ProductVO product = produceProductVOLiverpoolTv();
        int initialHashcode = product.hashCode();
        assertEquals(initialHashcode, product.hashCode());
        assertEquals(initialHashcode, product.hashCode());
    }

    @Test
    public void shouldHaveSameHashcodeForEqualObjects() {
        int hashProductA = produceProductVOLiverpoolTv().hashCode();
        int hashProductB = produceProductVOLiverpoolTv().hashCode();
        assertEquals(hashProductA, hashProductB);
    }

    @Test
    public void shouldHaveDifferentHashcodeForNonEqualObjects() {
        int hashProductA = produceProductVOLiverpoolTv().hashCode();
        int hashProductB = produceProductVOSkySportNews().hashCode();
        assertTrue(hashProductA != hashProductB);
    }

    @Test
    public void shouldHaveToString() {
        String product = produceProductVOLiverpoolTv().toString();
        String expected = "ProductVO{name=LiverpoolTV, category=Sport, locationId=Optional.of(Liverpool)}";
        assertEquals(expected, product);
    }

    @Test
    public void shouldBeImmutable() {
        assertInstancesOf(produceProductVOLiverpoolTv().getClass(), areImmutable(), provided(Optional.class).isAlsoImmutable());
    }

    @Test
    public void shouldBeDeserializable() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"name\":\"LiverpoolTV\",\"category\":\"Sport\", \"locationId\":\"Liverpool\"}";
        ProductVO productVO = objectMapper.readValue(json.getBytes(), ProductVO.class);
        assertEquals(productVO, produceProductVOLiverpoolTv());
    }

    @Test
    public void shouldBeDeserializable_ForOptionalFields() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\"name\":\"SkySportNews\",\"category\":\"News\", \"locationId\":\"null\"}";
        ProductVO productVO = objectMapper.readValue(json.getBytes(), ProductVO.class);
        assertEquals(productVO, produceProductVOSkySportNews());
    }

    @Test
    public void shouldBeSerializable() throws Exception {
        String serialized = Serializer.jsonSerializer(produceProductVOLiverpoolTv());
        String expected = "{\"name\":\"LiverpoolTV\",\"category\":\"Sport\",\"locationId\":\"Liverpool\"}";
        assertEquals(expected,serialized);
    }

}
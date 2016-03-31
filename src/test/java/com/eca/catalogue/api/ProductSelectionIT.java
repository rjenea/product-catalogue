package com.eca.catalogue.api;


import com.eca.CatalogueAppConfiguration;
import com.eca.catalogue.product.repository.ProductRepository;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import java.nio.charset.Charset;

import static com.eca.test.util.IsValidProductMatcher.matchesWith;
import static com.eca.test.util.ProductMother.*;
import static com.eca.test.util.ProductMother.produceProductArsenalTv;
import static com.eca.test.util.Serializer.jsonSerializer;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CatalogueAppConfiguration.class)
@WebAppConfiguration
public class ProductSelectionIT {

    private MediaType contentType;
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setup() throws Exception {
        contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8"));
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void addProduct() throws Exception {
        prepareTestData();
        String skySportNews = jsonSerializer(produceProductVOGlasgowTv());
        this.mockMvc.perform(post("/api/catalogue/products")
                .contentType(contentType)
                .content(skySportNews))
                .andExpect(status().isCreated());
        assertThat(productRepository.findAll(), prepareMatcherForTestData());
    }

    @Test
    public void customerId_ShouldBeProvided() throws Exception {
        mockMvc.perform(get("/api/catalogue/products")).andExpect(status().isNotFound());
    }

    @Test
    public void shouldHaveValidProducts_ForLondon() throws Exception {
        prepareTestData();
        mockMvc.perform(get("/api/catalogue/products").cookie(new Cookie("customerId", "123")))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("ArsenalTV")))
                .andExpect(jsonPath("$[1].name", is("SkyNews")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn404_ForUnKnownCustomerId() throws Exception {
        mockMvc.perform(get("/api/catalogue/products").cookie(new Cookie("customerId", "333"))).andExpect(status().isNotFound());
    }

    private void prepareTestData() {
        productRepository.deleteAll();
        productRepository.save(produceProductArsenalTv());
        productRepository.save(produceProductLiverpoolTv());
        productRepository.save(produceProductSkyNews());
    }

    private Matcher prepareMatcherForTestData() {
        Matcher arsenalMatcher = matchesWith(produceProductArsenalTv());
        Matcher liverpoolMatcher = matchesWith(produceProductLiverpoolTv());
        Matcher skyNewsMatcher = matchesWith(produceProductSkyNews());
        Matcher glasgowMatcher = matchesWith(produceProductGlasgowTv());
        return containsInAnyOrder(arsenalMatcher, skyNewsMatcher, liverpoolMatcher, glasgowMatcher);
    }
}
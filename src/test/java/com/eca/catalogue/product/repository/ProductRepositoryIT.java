package com.eca.catalogue.product.repository;

import com.mongodb.Mongo;
import com.eca.catalogue.product.model.Product;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.eca.test.util.IsValidProductMatcher.matchesWith;
import static com.eca.test.util.ProductMother.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ProductRepositoryIT {

    @Autowired
    private ProductRepository repository;

    @Before
    public void setUp() {
        prepareTestData();
    }

    @Test
    public void findByLocationIdOrLocationIdIsNull_ShouldReturnEmptyList_WhenDataBaseIsEmpty() {
        repository.deleteAll();
        List<Product> products = repository.findByLocationIdOrLocationIdIsNull(null);
        assertThat(products, empty());
    }

    @Test
    public void findByLocationIdOrLocationIdIsNull_ShouldReturnCountyWideProducts_ForNullLocationId() {
        List<Product> products = repository.findByLocationIdOrLocationIdIsNull(null);
        assertThat(products, contains(matchesWith(produceProductSkyNews())));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void findByLocationIdOrLocationIdIsNull_ShouldReturnLocationSpecificAndCountryWideProducts_ForKnownLocationId() {
        List<Product> products = repository.findByLocationIdOrLocationIdIsNull("London");
        assertThat(products, prepareLondonMatcher());
    }

    @Test
    public void findByLocationIdOrLocationIdIsNull_ShouldReturnCountyWideProducts_ForUnknownLocationId() {
        List<Product> products = repository.findByLocationIdOrLocationIdIsNull("Glasgow");
        assertThat(products, contains(matchesWith(produceProductSkyNews())));
    }

    private void prepareTestData() {
        repository.deleteAll();
        repository.save(produceProductArsenalTv());
        repository.save(produceProductLiverpoolTv());
        repository.save(produceProductSkyNews());
    }

    @SuppressWarnings("unchecked")
    private Matcher prepareLondonMatcher() {
        Matcher arsenalMatcher = matchesWith(produceProductArsenalTv());
        Matcher skyNewsMatcher = matchesWith(produceProductSkyNews());
        return containsInAnyOrder(arsenalMatcher, skyNewsMatcher);
    }

    @Configuration
    @EnableMongoRepositories
    static class Config extends AbstractMongoConfiguration {

        @Override
        protected String getDatabaseName() {
            return "integration_test_db";
        }

        @Override
        public Mongo mongo() throws Exception {
            return new EmbeddedMongoBuilder()
                    .version("2.6.1")
                    .bindIp("127.0.0.1")
                    .build();
        }
    }

}
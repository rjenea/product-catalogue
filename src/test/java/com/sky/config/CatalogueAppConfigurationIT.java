package com.sky.config;

import com.sky.CatalogueAppConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CatalogueAppConfiguration.class)
@WebAppConfiguration
public class CatalogueAppConfigurationIT {

    @Test
    public void applicationShouldStart_withGivenConfiguration() {
    }

}
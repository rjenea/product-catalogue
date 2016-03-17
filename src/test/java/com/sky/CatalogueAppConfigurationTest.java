package com.sky;

import com.google.common.base.Preconditions;
import com.mongodb.Mongo;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import org.assertj.core.api.Condition;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.test.OutputCapture;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.assertj.core.api.Assertions.assertThat;

public class CatalogueAppConfigurationTest {

    private static final String SPRING_STARTUP = "root of context hierarchy";
    private static final String STARTED_CATALOGUE_APP = "Started CatalogueAppConfiguration";
    private static final String[] APP_PARMETERS = new String[]{
            "--spring.main.webEnvironment=true",
            "--server.port=0",
            "--spring.data.mongodb.port=0",
            "--spring.mongodb.embedded.port=0"};

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    private ConfigurableApplicationContext context;

    @After
    public void close() {
        if (this.context != null) {
            this.context.close();
        }
    }

    @Test
    public void shouldBeApplicationContext() throws Exception {
        SpringApplication application = new SpringApplication(CatalogueAppConfiguration.class);
        this.context = application.run(APP_PARMETERS);
        assertThat(this.context).isInstanceOf(AnnotationConfigEmbeddedWebApplicationContext.class);
    }

    @Test
    public void shouldAcceptCommandLineProperty() throws Exception {
        SpringApplication application = new SpringApplication(CatalogueAppConfiguration.class);
        ConfigurableEnvironment environment = new StandardEnvironment();
        application.setEnvironment(environment);
        this.context = application.run(APP_PARMETERS);
        assertThat(environment).has(commandLinePropertyMatcher());
    }

    @Test
    public void shouldStartApplication_WithGivenArguments() throws Exception {
        CatalogueAppConfiguration.main(APP_PARMETERS);
        assertThat(outputCapture.toString()).contains(SPRING_STARTUP, STARTED_CATALOGUE_APP);
    }

    private Condition<ConfigurableEnvironment> commandLinePropertyMatcher() {
        return new Condition<ConfigurableEnvironment>("Property sources") {
            @Override
            public boolean matches(ConfigurableEnvironment value) {
                for (PropertySource source : value.getPropertySources()) {
                    if (CommandLinePropertySource.class.isInstance(source) && "commandLineArgs".equals(source.getName())) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    @Bean
    public Mongo mongo() throws IOException {
        System.setProperty("DB.TRACE","true");
        return new EmbeddedMongoBuilder()
                .version("2.6.10")
                .bindIp("127.0.0.1")
                .port(0)
                .build();
    }
}
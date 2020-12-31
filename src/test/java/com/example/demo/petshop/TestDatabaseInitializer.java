package com.example.demo.petshop;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@TestConfiguration
public class TestDatabaseInitializer {

    @Bean
    @Primary
    public ConnectionFactoryInitializer initializer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
        var initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        var populator = new ResourceDatabasePopulator(
                new ClassPathResource("clear_data.sql"),
                new ClassPathResource("schema.sql"),
                new ClassPathResource("data.sql")
        );
        initializer.setDatabasePopulator(populator);

        return initializer;
    }
}

package ru.youmiteru.backend.initializers;


import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.List;

public class PostgresInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final PostgreSQLContainer<?> POSTGRES_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:latest")
        .withDatabaseName("postgres")
        .withUsername("postgres")
        .withPassword("postgres");

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Startables.deepStart(List.of(POSTGRES_SQL_CONTAINER)).join();

        applicationContext.addApplicationListener(
            applicationEvent -> {
                if (applicationEvent instanceof ContextClosedEvent) {
                    POSTGRES_SQL_CONTAINER.stop();
                }
            }
        );

        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
            applicationContext,
            "spring.datasource.url=" + POSTGRES_SQL_CONTAINER.getJdbcUrl(),
            "spring.datasource.username=" + POSTGRES_SQL_CONTAINER.getUsername(),
            "spring.datasource.password=" + POSTGRES_SQL_CONTAINER.getPassword()
        );
    }
}

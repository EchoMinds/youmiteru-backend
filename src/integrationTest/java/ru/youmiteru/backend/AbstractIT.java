package ru.youmiteru.backend;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.youmiteru.backend.initializers.PostgresInitializer;

@ActiveProfiles("it")
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = BackendApplication.class
)
@ContextConfiguration(initializers = PostgresInitializer.class)
abstract public class AbstractIT {
}

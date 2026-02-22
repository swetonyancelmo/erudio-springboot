package br.com.swetonyancelmo.integrationtests.testcontainers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        static MySQLContainer<?> mySql = new MySQLContainer<>("mysql:9.6.0");

        private static void startContainers() {
            Startables.deepStart(Stream.of(mySql)).join();
        }

        private static Map<String, String> createConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url", mySql.getJdbcUrl(),
                    "spring.datasource.username", mySql.getUsername(),
                    "spring.datasource.password", mySql.getPassword()
            );
        }

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testContainers = new MapPropertySource("testcontainers",
                    (Map) createConnectionConfiguration());
            environment.getPropertySources().addFirst(testContainers);
        }

    }
}

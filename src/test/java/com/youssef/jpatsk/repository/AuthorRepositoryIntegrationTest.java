package com.youssef.jpatsk.repository;

import com.youssef.jpatsk.dao.AuthorRepository;
import com.youssef.jpatsk.entity.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Testcontainers
public class AuthorRepositoryIntegrationTest {

    @Container
    static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.36")
            .withDatabaseName("courses_app")
            .withUsername("root")
            .withPassword("admin");

    private final AuthorRepository repository;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository repository) {
        this.repository = repository;
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
        registry.add("spring.datasource.driverClassName", MY_SQL_CONTAINER::getDriverClassName);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
        registry.add("spring.jpa.hibernate.naming.physical-strategy",
                () -> "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
    }

    @BeforeEach
    public void setup() {
        repository.save(new Author(1L, "Alice", "alice@example.com", new Date()));
    }

    @Test
    public void getAuthorByEmail_whenEmailExists_returnsNonEmptyOptional() {
        Optional<Author> result = repository.findByEmail("alice@example.com");

        assertTrue(result.isPresent());
        assertEquals("Alice", result.get().getName());
    }

    @Test
    public void getAuthorByEmail_whenEmailDoesNotExist_returnsEmptyOptional() {
        Optional<Author> result = repository.findByEmail("test@test.com");

        assertTrue(result.isEmpty());
    }
}

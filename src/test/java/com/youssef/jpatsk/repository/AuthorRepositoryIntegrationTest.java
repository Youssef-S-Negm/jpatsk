package com.youssef.jpatsk.repository;

import com.youssef.jpatsk.dao.AuthorRepository;
import com.youssef.jpatsk.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class AuthorRepositoryIntegrationTest {

    private final AuthorRepository repository;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository repository) {
        this.repository = repository;
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

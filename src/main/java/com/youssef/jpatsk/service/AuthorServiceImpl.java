package com.youssef.jpatsk.service;

import com.youssef.jpatsk.dao.AuthorRepository;
import com.youssef.jpatsk.entity.Author;
import com.youssef.jpatsk.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findByEmail(String email) {
        Optional<Author> result = authorRepository.findByEmail(email);

        if (result.isEmpty()) {
            throw new EntityNotFoundException("Couldn't find user email - " + email);
        }

        return result.get();
    }
}

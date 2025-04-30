package com.youssef.jpatsk.service;

import com.youssef.jpatsk.dao.AuthorRepository;
import com.youssef.jpatsk.dto.AuthorDto;
import com.youssef.jpatsk.dto.DtoMapper;
import com.youssef.jpatsk.entity.Author;
import com.youssef.jpatsk.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final DtoMapper mapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, DtoMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Override
    public AuthorDto findByEmail(String email) {
        Optional<Author> result = authorRepository.findByEmail(email);

        if (result.isEmpty()) {
            throw new EntityNotFoundException("Couldn't find user email - " + email);
        }

        return mapper.authorToAuthorDto(result.get());
    }
}

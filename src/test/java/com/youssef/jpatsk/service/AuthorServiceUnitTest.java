package com.youssef.jpatsk.service;

import com.youssef.jpatsk.dao.AuthorRepository;
import com.youssef.jpatsk.dto.AuthorDto;
import com.youssef.jpatsk.dto.DtoMapper;
import com.youssef.jpatsk.entity.Author;
import com.youssef.jpatsk.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceUnitTest {

    @Mock
    private AuthorRepository repository;

    @Mock
    private DtoMapper mapper;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    public void getAuthorByEmail_whenEmailExists_returnsAuthorDto() {
        Date date = new Date();
        Author author = new Author(1L, "Alice", "alice@gmail.com", date);
        AuthorDto dto = new AuthorDto();
        dto.setId(1L);
        dto.setName("Alice");
        dto.setEmail("alice@gmail.com");
        dto.setBirthDate(date);

        when(repository.findByEmail("alice@gmail.com"))
                .thenReturn(Optional.of(author));
        when(mapper.authorToAuthorDto(author))
                .thenReturn(dto);

        AuthorDto result = authorService.findByEmail("alice@gmail.com");

        assertNotNull(result);
        assertEquals("Alice", result.getName());

        verify(repository).findByEmail("alice@gmail.com");
        verify(mapper).authorToAuthorDto(author);
    }

    @Test
    public void getAuthorByEmail_whenEmailDoesNotExist_throwsEntityNotFoundException() {
        when(repository.findByEmail("alice@gmail.com"))
                .thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> authorService.findByEmail("alice@gmail.com")
        );

        assertEquals("Couldn't find author email - alice@gmail.com", exception.getMessage());

        verify(repository).findByEmail("alice@gmail.com");
    }
}

package com.youssef.jpatsk.service;

import com.youssef.jpatsk.dto.AuthorDto;

public interface AuthorService {

    AuthorDto findByEmail(String email);

}

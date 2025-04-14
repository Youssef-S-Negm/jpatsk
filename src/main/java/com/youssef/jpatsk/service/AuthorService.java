package com.youssef.jpatsk.service;

import com.youssef.jpatsk.entity.Author;

public interface AuthorService {

    Author findByEmail(String email);

}

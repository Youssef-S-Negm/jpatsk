package com.youssef.jpatsk.service;

import com.youssef.jpatsk.client.PostClient;
import com.youssef.jpatsk.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostClient client;

    public PostServiceImpl(PostClient client) {
        this.client = client;
    }

    @Override
    public List<PostDto> getPosts() {
        return client.getPosts();
    }
}

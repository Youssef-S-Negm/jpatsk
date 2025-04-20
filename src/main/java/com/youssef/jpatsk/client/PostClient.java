package com.youssef.jpatsk.client;

import com.youssef.jpatsk.dto.PostDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
public class PostClient {

    private final RestTemplate restTemplate;
    private final String url;

    public PostClient(RestTemplate restTemplate, @Value("${external.api.posts}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public List<PostDto> getPosts() {
        return List.of(Objects.requireNonNull(restTemplate.getForObject(url, PostDto[].class)));
    }
}

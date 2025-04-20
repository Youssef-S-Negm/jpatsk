package com.youssef.jpatsk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.youssef.jpatsk.client.PostClient;
import com.youssef.jpatsk.dto.PostDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(WireMockExtension.class)
@AutoConfigureMockMvc
class PostControllerIntegrationTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostClient postClient;

    @RegisterExtension
    static WireMockExtension wireMockExtension = WireMockExtension.newInstance()
            .options(WireMockConfiguration.wireMockConfig().dynamicPort())
            .build();

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        String fullPath = wireMockExtension.baseUrl() + "/posts";

        registry.add("external.api.posts", () -> fullPath);
    }

    @Test
    void getPosts_returnsPostDtoList() throws Exception {
        PostDto dto = new PostDto();
        dto.setId(1L);
        dto.setUserId(1L);
        dto.setBody("Test body");
        dto.setTitle("Test Title");

        wireMockExtension.stubFor(WireMock.get("/posts").willReturn(WireMock.aResponse()
                .withHeader("content-type", "application/json")
                .withBody(mapper.writeValueAsString(List.of(dto)))));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

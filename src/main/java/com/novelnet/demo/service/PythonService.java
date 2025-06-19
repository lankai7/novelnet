package com.novelnet.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

@Service
public class PythonService {

    @Value("${python.host}")
    private String pythonHost;

    private final RestTemplate restTemplate = new RestTemplate();

    public String scrapeBook(String bookId, String bookType) {
        String url = pythonHost + "/scrape";

        // 构造请求体
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("book_id", bookId);
        requestBody.put("book_type", bookType);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        // 发送请求
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        return response.getBody();
    }
}

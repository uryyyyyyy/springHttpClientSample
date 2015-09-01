package com.example.http.client;

import java.io.IOException;
import java.util.Set;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    private static final RestTemplate rest = new RestTemplate();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        String res = HttpUtil.httpGetJson(rest, "http://google.com", "variable");
        JavaType type = mapper.getTypeFactory().constructCollectionType(Set.class, String.class);
        Set<String> aa =  mapper.readValue(res, type);
    }
}

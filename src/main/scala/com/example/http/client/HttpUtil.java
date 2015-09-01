package com.example.http.client;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class HttpUtil {

    public static String httpGetJson(RestTemplate rest, String url, Object... uriVariables)throws IOException {
        String trueUrl = url + " --params " + Arrays.toString(uriVariables);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Accept", "application/json");
            HttpEntity<String> requestEntity = new HttpEntity<>("", headers);

            ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.GET, requestEntity, String.class, uriVariables);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            }
            String reason = responseEntity.getStatusCode().getReasonPhrase();
            String code = responseEntity.getStatusCode().toString();
            throw new IOException("url: " + trueUrl + ", code: " + code + ", reason: " + reason);
        }catch (HttpStatusCodeException e){
            throw new IOException("url: " + trueUrl + ". error : " + e.getMessage(), e);
        }
    }

    public static String httpPostJson(RestTemplate rest, String url, String postObj, Object... uriVariables) throws IOException{
        String trueUrl = url + " --params " + Arrays.toString(uriVariables);
        System.out.println(trueUrl);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Accept", "application/json");
            HttpEntity<String> requestEntity = new HttpEntity<>(postObj, headers);
            ResponseEntity<String> responseEntity = rest.exchange(url, HttpMethod.POST, requestEntity, String.class, uriVariables);
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                return responseEntity.getBody();
            }
            String reason = responseEntity.getStatusCode().getReasonPhrase();
            String code = responseEntity.getStatusCode().toString();
            throw new IOException("url: " + trueUrl + ", code: " + code + ", reason: " + reason + ", postData: " + postObj);
        }catch (HttpStatusCodeException e){
            throw new IOException("url: " + trueUrl, e);
        }
    }

    public static String uploadFile(RestTemplate rest, File file, String url) throws IOException{
        try {
            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
            parts.add("file", new FileSystemResource(file));
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
            return rest.postForObject(builder.toUriString(), parts, String.class);
        }catch (HttpStatusCodeException e){
            throw new IOException("url: " + url, e);
        }
    }
}

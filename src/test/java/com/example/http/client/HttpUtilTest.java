package com.example.http.client;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtilTest {

    static final RestTemplate rest = new RestTemplate();
    static final ObjectMapper mapper = new ObjectMapper();

    @Test(expected = IOException.class)
    public void testHttpGetJsonFail() throws Exception {
        String res = HttpUtil.httpGetJson(rest, "http://google.com/aho");
        System.out.println(res);
    }

    @Test
    public void testHttpGetJson() throws Exception {
        String res = HttpUtil.httpGetJson(rest, "http://date.jsontest.com");
        System.out.println(res);
        DummyDto tag = mapper.readValue(res, DummyDto.class);
        System.out.println(tag.milliseconds_since_epoch);
    }

    @Test
    public void testHttpGetJson2() throws Exception {
        String res = HttpUtil.httpGetJson(rest, "http://{target}.jsontest.com", "date");
        System.out.println(res);
        DummyDto tag = mapper.readValue(res, DummyDto.class);
        System.out.println(tag.milliseconds_since_epoch);
    }

    @Test
    public void testHttpPostJson() throws Exception {
        DummyDto dto = new DummyDto("aa", 11, "bb");

        String postData = mapper.writeValueAsString(dto);
        String res = HttpUtil.httpPostJson(rest, "http://date.jsontest.com", postData);
        System.out.println(res);
        DummyDto tag = mapper.readValue(res, DummyDto.class);
        System.out.println(tag.milliseconds_since_epoch);
    }

    @Test
    public void testHttpPostJson2() throws Exception {
        String postData = mapper.writeValueAsString("aa");
        System.out.println(postData);
        String res = HttpUtil.httpPostJson(rest, "http://date.jsontest.com", postData);
        System.out.println(res);
    }

    @Test
    public void testHttpPostJson3() throws Exception {
        String postData = mapper.writeValueAsString(100);
        System.out.println(postData);
        String res = HttpUtil.httpPostJson(rest, "http://date.jsontest.com", postData);
        System.out.println(res);
    }

    @Test
    public void testUploadFile() throws Exception {
        File file = new File("dummyFile");
        HttpUtil.uploadFile(rest, file, "http://google.com");
    }
}
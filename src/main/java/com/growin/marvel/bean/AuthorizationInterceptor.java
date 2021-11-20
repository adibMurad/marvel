package com.growin.marvel.bean;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class AuthorizationInterceptor implements RequestInterceptor {
    @Value("${MARVEL_PUBLIC_KEY}")
    private String publicKey;
    @Value("${MARVEL_PRIVATE_KEY}")
    private String privateKey;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        long timestamp = System.currentTimeMillis();
        String password = timestamp + privateKey + publicKey;
        String hash = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        requestTemplate.query("ts", Long.toString(timestamp));
        requestTemplate.query("apikey", publicKey);
        requestTemplate.query("hash", hash);
    }
}

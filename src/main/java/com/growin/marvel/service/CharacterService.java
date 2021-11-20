package com.growin.marvel.service;

import com.growin.marvel.client.CharacterFeignClient;
import com.growin.marvel.dto.CharacterResults;
import com.growin.marvel.model.MarvelCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CharacterService {
    private static final String PUBLIC_KEY = "ec032753130a652a497cbd47361fd8d1";
    private static final String PRIVATE_KEY = "2af2c85b784f0f93bf6f1ebb8e69c1a17e2fa843";

    @Autowired
    CharacterFeignClient feignClient;

    public List<BigInteger> getAllCharacterIds() {
        long timestamp = System.currentTimeMillis();
        String password = timestamp + PRIVATE_KEY + PUBLIC_KEY;
        String hash = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        CharacterResults results = feignClient.getAllCharacters(0, 1, timestamp, PUBLIC_KEY, hash);
        return null;
    }

    public MarvelCharacter getCharacter(BigInteger id) {
        return null;
    }
}

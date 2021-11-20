package com.growin.marvel.service;

import com.growin.marvel.client.CharacterFeignClient;
import com.growin.marvel.mapper.CharacterMapper;
import com.growin.marvel.model.MarvelCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {
    @Autowired
    CharacterFeignClient feignClient;
    @Autowired
    CharacterMapper mapper;

    public List<Integer> getAllCharacterIds() {
        return mapper.mapResultsToIdList(feignClient.getAllCharacters(0, 1));
    }

    public MarvelCharacter getCharacter(int id) {
        return mapper.mapResultsToCharacter(feignClient.getCharacter(id));
    }
}

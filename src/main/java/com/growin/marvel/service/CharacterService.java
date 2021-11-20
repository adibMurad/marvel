package com.growin.marvel.service;

import com.growin.marvel.client.CharacterFeignClient;
import com.growin.marvel.dto.CharacterResults;
import com.growin.marvel.mapper.CharacterMapper;
import com.growin.marvel.model.MarvelCharacter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CharacterService {
    @Autowired
    CharacterFeignClient feignClient;
    @Autowired
    CharacterMapper mapper;

    private CharacterResults getCharacterResults() {
        return feignClient.getAllCharacters(0, 2);
    }

    @Cacheable(value = "idCache")
    public List<Integer> getAllCharacterIds() {
        log.info("Id cache refreshed.");
        return mapper.mapResultsToIdList(getCharacterResults());
    }

    @Cacheable(value = "characterCache", key = "#id")
    public MarvelCharacter getCharacter(int id) {
        log.info("Character cache refreshed.");
        return mapper.mapResultsToCharacter(feignClient.getCharacter(id));
    }
}

package com.growin.marvel.service;

import com.growin.marvel.client.CharacterFeignClient;
import com.growin.marvel.dto.CharacterResults;
import com.growin.marvel.exception.CharacterServiceException;
import com.growin.marvel.mapper.CharacterMapper;
import com.growin.marvel.model.MarvelCharacter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CharacterService {
    @Autowired
    CharacterFeignClient characterClient;
    @Autowired
    CharacterMapper mapper;

    @Value("${character.request.limit}")
    private int limit;

    @Cacheable(value = "idCache")
    public List<Integer> getAllCharacterIds() {
        log.info("Id cache refreshed.");
        List<Integer> idList = new ArrayList<>();
        int offset = 0;
        CharacterResults results = getAllCharacters(offset);
        while (results.getData().getCount() > 0) {
            idList.addAll(mapper.mapResultsToIdList(results));
            offset += limit;
            results = getAllCharacters(offset);
        }
        return idList;
    }

    private CharacterResults getAllCharacters(int offset) {
        CharacterResults results = characterClient.getAllCharacters(offset, limit);
        validateResultStatus(results);
        return results;
    }

    private void validateResultStatus(CharacterResults results) {
        if (results.getCode() != HttpStatus.OK.value()) {
            throw new CharacterServiceException(results.getCode(), results.getStatus());
        }
    }

    @Cacheable(value = "characterCache", key = "#id")
    public MarvelCharacter getCharacter(int id) {
        log.info("Character cache refreshed.");
        CharacterResults results = characterClient.getCharacter(id);
        validateResultStatus(results);
        return mapper.mapResultsToCharacter(results);
    }

}

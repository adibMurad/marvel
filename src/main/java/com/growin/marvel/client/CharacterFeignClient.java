package com.growin.marvel.client;

import com.growin.marvel.dto.CharacterResults;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "characters", url = "https://gateway.marvel.com:443/v1/public/characters")
public interface CharacterFeignClient {
    @GetMapping
    CharacterResults getAllCharacters(@RequestParam int offset,
                                      @RequestParam int limit);

    @GetMapping("/{characterId}")
    CharacterResults getCharacter(@PathVariable int characterId);
}

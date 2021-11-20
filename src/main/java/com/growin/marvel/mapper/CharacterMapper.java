package com.growin.marvel.mapper;

import com.growin.marvel.dto.CharacterResults;
import com.growin.marvel.model.MarvelCharacter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CharacterMapper {
    public List<Integer> mapResultsToIdList(CharacterResults results) {
        return results.getData().getResults().stream().map(CharacterResults.Result::getId).collect(Collectors.toList());
    }

    public MarvelCharacter mapResultsToCharacter(CharacterResults results) {
        return results.getData().getResults()
                .stream()
                .map((result) -> {
                    MarvelCharacter character = new MarvelCharacter();
                    character.setId(result.getId());
                    character.setName(result.getName());
                    character.setDescription(result.getDescription());
                    character.setThumbnail(result.getThumbnail());
                    return character;
                })
                .findFirst()
                .orElse(null);
    }
}

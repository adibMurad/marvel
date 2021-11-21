package com.growin.marvel.controller;

import com.growin.marvel.model.MarvelCharacter;
import com.growin.marvel.service.CharacterService;
import com.growin.marvel.service.TranslationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/characters")
@Tag(name = "character-controller", description = "API to query Marvel characters data.")
public class CharacterController {
    @Autowired
    private CharacterService characterService;
    @Autowired
    private TranslationService translationService;

    @ApiOperation(
            value = "Retrieve all the Marvel character ids.",
            notes = "Retrieve all the Marvel character ids only, in an array of numbers.",
            tags = {"character-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success."),
            @ApiResponse(code = 500, message = "An unexpected error has occurred. The error has been logged and is being investigated.")}
    )
    @GetMapping
    public ResponseEntity<List<Integer>> getAllCharacterIds() {
        return ResponseEntity.ok(characterService.getAllCharacterIds());
    }

    @ApiOperation(
            value = "Retrieve summary data of a Marvel character.",
            notes = "Retrieve id, name, description and thumbnail of a Marvel character.",
            tags = {"character-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success."),
            @ApiResponse(code = 500, message = "An unexpected error has occurred. The error has been logged and is being investigated.")}
    )
    @GetMapping("/{characterId}")
    public ResponseEntity<MarvelCharacter> getCharacter(@PathVariable int characterId,
                                                        @RequestParam(required = false) String language) {
        MarvelCharacter character = characterService.getCharacter(characterId);
        translationService.translateDescription(character, language);
        return ResponseEntity.ok(character);
    }

}

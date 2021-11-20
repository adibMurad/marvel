package com.growin.marvel.controller;

import com.growin.marvel.model.MarvelCharacter;
import com.growin.marvel.service.CharacterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/characters")
@Tag(name = "character-controller", description = "API to query Marvel characters data.")
public class CharacterController {
    @Autowired
    private CharacterService service;

    @ApiOperation(
            value = "Retrieve all the Marvel character ids.",
            notes = "Retrieve all the Marvel character ids only, in an array of numbers.",
            tags = {"character-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = ""),
            @ApiResponse(code = 401, message = ""),
            @ApiResponse(code = 422, message = ""),
            @ApiResponse(code = 500, message = "")}
    )
    @GetMapping
    public ResponseEntity<List<Integer>> getAllCharacterIds() {
        return ResponseEntity.ok(service.getAllCharacterIds());
    }

    @ApiOperation(
            value = "Retrieve summary data of a Marvel character.",
            notes = "Retrieve id, name, description and thumbnail of a Marvel character.",
            tags = {"character-controller"}
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = ""),
            @ApiResponse(code = 401, message = ""),
            @ApiResponse(code = 422, message = ""),
            @ApiResponse(code = 500, message = "")}
    )
    @GetMapping("/{characterId}")
    public ResponseEntity<MarvelCharacter> getCharacter(@PathVariable int characterId) {
        return ResponseEntity.ok(service.getCharacter(characterId));
    }

}

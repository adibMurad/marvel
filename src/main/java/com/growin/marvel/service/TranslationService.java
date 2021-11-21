package com.growin.marvel.service;

import com.growin.marvel.client.TranslationFeignClient;
import com.growin.marvel.dto.TranslationRequest;
import com.growin.marvel.dto.TranslationResult;
import com.growin.marvel.model.MarvelCharacter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TranslationService {
    @Autowired
    TranslationFeignClient translationClient;
    @Value("${TRANSLATION_API_KEY}")
    private String translationAPIKey;

    public void translateDescription(MarvelCharacter character, String targetLanguage) {
        if (!StringUtils.hasText(targetLanguage)) {
            return;
        }
        List<TranslationRequest> textList = new ArrayList<>();
        textList.add(new TranslationRequest(character.getDescription()));
        List<TranslationResult> translationResults = translationClient.translate(
                targetLanguage,
                textList,
                translationAPIKey
        );
        String newDescription =
                translationResults
                        .stream()
                        .findFirst().orElseThrow()
                        .getTranslations()
                        .stream()
                        .findFirst().orElseThrow()
                        .getText();
        character.setDescription(newDescription);
    }
}


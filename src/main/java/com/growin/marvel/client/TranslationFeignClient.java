package com.growin.marvel.client;

import com.growin.marvel.dto.TranslationRequest;
import com.growin.marvel.dto.TranslationResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(value = "translation", url = "https://api.cognitive.microsofttranslator.com/")
public interface TranslationFeignClient {
    @PostMapping(path = "/translate",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    List<TranslationResult> translate(@RequestParam String to,
                                      @RequestBody List<TranslationRequest> text,
                                      @RequestHeader("Ocp-Apim-Subscription-Key") String subscriptionKey
    );
}

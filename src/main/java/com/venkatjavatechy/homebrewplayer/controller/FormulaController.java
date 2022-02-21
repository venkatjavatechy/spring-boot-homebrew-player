package com.venkatjavatechy.homebrewplayer.controller;

import com.venkatjavatechy.homebrewplayer.dto.FormulaResponse;
import com.venkatjavatechy.homebrewplayer.exceptions.FormulaNotFoundException;
import com.venkatjavatechy.homebrewplayer.exceptions.FormulaServiceApiException;
import com.venkatjavatechy.homebrewplayer.interfaces.AbstractController;
import com.venkatjavatechy.homebrewplayer.interfaces.FormulaApi;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.RetryCallback;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

@RestController
public class FormulaController extends AbstractController implements FormulaApi {

    @Override
    public ResponseEntity<FormulaResponse> getFormulaByName(@RequestParam("name") String name) {
        try {
            logger.info("Formula param name received for querying {}", name);
            String formattedUrl = this.formulaUrl + String.format("%s.json", name);
            logger.info("formattedUrl {}", formattedUrl);
            AtomicReference<FormulaResponse> response = new AtomicReference<>();
            this.retryTemplate.execute((RetryCallback<Object, FormulaServiceApiException>) retryContext -> {
                try {
                    ResponseEntity<FormulaResponse> responseEntity = this.restTemplate.exchange(formattedUrl, HttpMethod.GET, this.buildHttpEntity(),
                            FormulaResponse.class);
                    response.set(responseEntity.getBody());
                } catch (Exception e) {
                    String message = "Formula Api is not responding currently. Retrying...";
                    logger.warn(message);
                    throw new FormulaServiceApiException(message);
                }

                if (response.get() == null) {
                    String message = String.format("Formula name %s not found, please try different one", name);
                    logger.error(message);
                    throw new FormulaNotFoundException(message);
                }
                return response.get();
            });

            logger.info("Received response for formula {}", response);
            return ResponseEntity.ok().body(response.get());
        } catch (Exception e) {
            String message = "Formula Api is not responding currently. Please reach out to production support team.";
            logger.error(message);
            throw new FormulaServiceApiException(message);
        }
    }
}

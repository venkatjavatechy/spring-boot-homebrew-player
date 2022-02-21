package com.venkatjavatechy.homebrewplayer.interfaces;

import com.venkatjavatechy.homebrewplayer.dto.FormulaResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/homebrew")
public interface FormulaApi {

    @GetMapping(value = "/formula", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FormulaResponse> getFormulaByName(@RequestParam("name") String name);
}

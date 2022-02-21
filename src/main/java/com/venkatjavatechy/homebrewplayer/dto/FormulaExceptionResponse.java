package com.venkatjavatechy.homebrewplayer.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FormulaExceptionResponse {
    private String message;
    private LocalDateTime dateTime;
}

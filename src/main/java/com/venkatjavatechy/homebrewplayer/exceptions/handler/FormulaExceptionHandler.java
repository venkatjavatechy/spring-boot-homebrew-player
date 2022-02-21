package com.venkatjavatechy.homebrewplayer.exceptions.handler;

import com.venkatjavatechy.homebrewplayer.dto.FormulaExceptionResponse;
import com.venkatjavatechy.homebrewplayer.exceptions.FormulaNotFoundException;
import com.venkatjavatechy.homebrewplayer.exceptions.FormulaServiceApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class FormulaExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FormulaServiceApiException.class)
    public ResponseEntity<Object> handleExceptions(FormulaServiceApiException exception) {
        FormulaExceptionResponse response = new FormulaExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage(exception.getMessage());
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    @ExceptionHandler(FormulaNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(FormulaNotFoundException exception) {
        FormulaExceptionResponse response = new FormulaExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage(exception.getMessage());
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

}

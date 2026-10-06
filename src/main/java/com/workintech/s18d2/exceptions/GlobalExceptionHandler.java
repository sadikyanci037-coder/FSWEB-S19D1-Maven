package com.workintech.s18d2.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<PlantErrorResponse> handlePlantException(PlantException exception) {

        log.error("PlantException oluştu: {}", exception.getMessage());

        PlantErrorResponse response = new PlantErrorResponse(
                exception.getHttpStatus().value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, exception.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<PlantErrorResponse> handleException(Exception exception) {

        log.error("Beklenmeyen hata oluştu: ", exception);

        PlantErrorResponse response = new PlantErrorResponse(
                500,
                "Beklenmeyen bir hata oluştu.",
                System.currentTimeMillis()
        );

        return ResponseEntity.internalServerError().body(response);
    }
}
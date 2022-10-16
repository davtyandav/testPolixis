package com.davtyandav.testPolixis.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ExceptionDto {
    private final HttpStatus status;
    private final String message;
}

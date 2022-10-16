package com.davtyandav.testPolixis.exception;

import com.davtyandav.testPolixis.dto.ExceptionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {
    private final ExceptionDto exceptionDto;
}

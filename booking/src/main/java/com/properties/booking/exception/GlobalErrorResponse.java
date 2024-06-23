package com.properties.booking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GlobalErrorResponse {
    private String statusCode;
    private String message;
}

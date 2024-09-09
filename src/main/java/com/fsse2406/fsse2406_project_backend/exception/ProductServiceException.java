package com.fsse2406.fsse2406_project_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductServiceException extends RuntimeException {
    public ProductServiceException() {
    }

    public ProductServiceException(String message) {
        super(message);
    }
}

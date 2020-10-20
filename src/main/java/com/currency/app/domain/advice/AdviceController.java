package com.currency.app.domain.advice;


import com.currency.app.domain.exchange.api.UnknownCurrencyProvidedException;
import com.currency.app.domain.user.service.exception.InvalidPeselException;
import com.currency.app.domain.user.service.exception.UserAlreadyExistsException;
import com.currency.app.domain.user.service.exception.UserUnder18YearsOldException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
                              InvalidPeselException.class, UserUnder18YearsOldException.class, UserAlreadyExistsException.class,
                              UnknownCurrencyProvidedException.class
                      })
    public ResponseEntity<ApiError> handleExceptions(Exception exception, WebRequest webRequest) {
        ApiError apiError = ApiError.builder()
                                    .message(exception.getMessage())
                                    .status(HttpStatus.CONFLICT)
                                    .timestamp(LocalDateTime.now())
                                    .build();
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}

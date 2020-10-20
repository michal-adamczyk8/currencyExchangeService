package com.currency.app.api.advice;

import com.currency.app.api.exchange.UnknownCurrencyProvidedException;
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

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidPeselException.class, UserUnder18YearsOldException.class, UserAlreadyExistsException.class,
                       UnknownCurrencyProvidedException.class})
    public ResponseEntity<Object> handleExceptions(Exception exception, WebRequest webRequest) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
    }
}

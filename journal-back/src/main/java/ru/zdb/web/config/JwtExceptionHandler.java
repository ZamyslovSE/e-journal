package ru.zdb.web.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.zdb.web.model.ApiResponse;

@ControllerAdvice
public class JwtExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ExpiredJwtException.class })
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus("401", "Token has expired.");
        return handleExceptionInternal(ex, response,
                new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
}
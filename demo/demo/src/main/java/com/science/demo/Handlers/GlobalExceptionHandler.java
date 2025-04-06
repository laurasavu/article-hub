package com.science.demo.Handlers;
import com.science.demo.Exceptions.ArticleNotFoundException;
import com.science.demo.Exceptions.CommentNotFoundEXception;
import com.science.demo.Exceptions.WriterNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final HttpStatus NOT_FOUND_STATUS = HttpStatus.NOT_FOUND;

    @ExceptionHandler({ArticleNotFoundException.class, WriterNotFoundException.class, CommentNotFoundEXception.class})
    public ResponseEntity<Map<String, Object>> handleNotFoundExceptions(RuntimeException ex) {
        return buildErrorResponse(ex.getMessage(), NOT_FOUND_STATUS);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        return buildErrorResponse("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("status", status.value());
        errorBody.put("error", status.getReasonPhrase());
        errorBody.put("message", message);
        return new ResponseEntity<>(errorBody, status);
    }
}

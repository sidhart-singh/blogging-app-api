package com.sidhart.singh.blog.exceptions;

import com.sidhart.singh.blog.payloads.ApiResponse;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
        String message = e.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentsNotValidExceptionHandler(MethodArgumentNotValidException e){
        Map<String, String> responseMap = new HashMap<>();

//        we receive a list of ObjectErrors from getAllErrors() :
//        Map the field with appropriate error message received from the Validation Annotations defined in the UserDTO
        e.getBindingResult().getAllErrors().forEach(error -> {
//                Object error doesn't have getField() :
//                typeCast to FieldError
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            responseMap.put(fieldName, message);
        });

        return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
    }
}

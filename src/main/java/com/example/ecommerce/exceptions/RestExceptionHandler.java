package com.example.ecommerce.exceptions;

import com.example.ecommerce.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler
{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidationExceptions( MethodArgumentNotValidException ex)
    {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(HttpStatus.BAD_REQUEST);
        responseDto.setSuccess(false);
        responseDto.setErrorDetails( new ErrorDetails("not valid data",errors,new Date().getTime()));
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);

    }
}

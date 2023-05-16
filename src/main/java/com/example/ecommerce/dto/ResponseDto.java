package com.example.ecommerce.dto;

import com.example.ecommerce.exceptions.ErrorDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    private HttpStatus status;
    private boolean success;
    private Object data;
    private ErrorDetails errorDetails;
}

package com.example.ecommerce.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(String message)
    {
        super(message);
    }
}

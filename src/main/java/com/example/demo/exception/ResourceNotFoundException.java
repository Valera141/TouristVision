package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
    

    public ResourceNotFoundException(String resourceType, String identifier) {
        // Se pasa un mensaje al constructor de la clase padre (RuntimeException)
        super(String.format("El recurso %s con identificador %s no fue encontrado.", resourceType, identifier));
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

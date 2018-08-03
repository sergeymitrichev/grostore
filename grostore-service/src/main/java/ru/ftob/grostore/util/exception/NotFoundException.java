package ru.ftob.grostore.util.exception;

public class NotFoundException extends RuntimeException  {
    public NotFoundException(String message) {
        super(message);
    }
}

package ru.ssau.tk.respect.laboratorywork1.exceptions;

public class WrongNumberOfElementsException extends RuntimeException {

    private static final long serialVersionUID = -6579778123817550356L;

    public WrongNumberOfElementsException() {}

    public WrongNumberOfElementsException(String message) {
        super(message);
    }
}

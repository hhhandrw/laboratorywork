package ru.ssau.tk.respect.laboratorywork1.exceptions;

public class DifferentLengthOfArraysException extends RuntimeException {

    private static final long serialVersionUID = -1395445275085704504L;

    public DifferentLengthOfArraysException() {}

    public DifferentLengthOfArraysException(String message) {
        super(message);
    }
}

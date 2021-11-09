package ru.ssau.tk.respect.laboratorywork1.exceptions;

public class InconsistentFunctionsException extends RuntimeException{

    private static final long serialVersionUID = 7542197300300041361L;

    public InconsistentFunctionsException() {}

    public InconsistentFunctionsException(String message) {
        super(message);
    }
}

package ru.ssau.tk.respect.laboratorywork1.exceptions;

public class ArrayIsNotSortedException extends RuntimeException {

    private static final long serialVersionUID = 5489478429757033377L;

    public ArrayIsNotSortedException() {}

    public ArrayIsNotSortedException(String message) {
        super(message);
    }
}

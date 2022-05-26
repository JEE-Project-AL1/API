package com.esgi.jee.apijee.kernel.exceptions;

public class CreationImpossibleException extends RuntimeException{

    public CreationImpossibleException(String message) {
        super(message);
    }

    public CreationImpossibleException(String message, Throwable cause) {
        super(message, cause);
    }

}

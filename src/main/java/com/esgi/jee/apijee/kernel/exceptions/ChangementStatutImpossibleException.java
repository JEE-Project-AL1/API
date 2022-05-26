package com.esgi.jee.apijee.kernel.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ChangementStatutImpossibleException extends RuntimeException{

    public ChangementStatutImpossibleException(String message) {
        super(message);
    }

    public ChangementStatutImpossibleException(String message, Throwable cause) {
        super(message, cause);
    }
}

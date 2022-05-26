package com.esgi.jee.apijee.kernel.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityAlreadyExistingException extends RuntimeException {

    public EntityAlreadyExistingException(String message) {
        super(message);
    }

    public EntityAlreadyExistingException(String message, Throwable cause) {
        super(message, cause);
    }
}

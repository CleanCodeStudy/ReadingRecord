package com.javabom.toby.chapter4.exception;

public class CheckedException extends Exception {
    public CheckedException() {
        super();
    }

    public CheckedException(final String message) {
        super(message);
    }

    public CheckedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CheckedException(final Throwable cause) {
        super(cause);
    }

    protected CheckedException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

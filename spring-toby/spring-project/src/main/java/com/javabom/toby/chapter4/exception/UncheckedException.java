package com.javabom.toby.chapter4.exception;

public class UncheckedException extends RuntimeException {
    public UncheckedException() {
        super();
    }

    public UncheckedException(final String message) {
        super(message);
    }

    public UncheckedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UncheckedException(final Throwable cause) {
        super(cause);
    }

    protected UncheckedException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

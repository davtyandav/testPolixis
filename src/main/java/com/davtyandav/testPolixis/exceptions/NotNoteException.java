package com.davtyandav.testPolixis.exceptions;

public class NotNoteException extends Exception{
    public NotNoteException() {
        this("Not found note");
    }

    public NotNoteException(String message) {
        super(message);
    }

    public NotNoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotNoteException(Throwable cause) {
        super(cause);
    }

    public NotNoteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

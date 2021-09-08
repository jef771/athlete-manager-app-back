package com.angularexample.angularcrud.error;

public class AthleteNotFoundException extends Exception{



    public AthleteNotFoundException(String message) {
        super(message);
    }

    public AthleteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AthleteNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AthleteNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

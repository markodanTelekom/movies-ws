package com.onboarding.movies.moviesws.exception;

public class Exception {
    private final String reason;
    private final String message;

    public Exception(String reason, String message){
        this.message = message;
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public String getReason() {
        return reason;
    }
}

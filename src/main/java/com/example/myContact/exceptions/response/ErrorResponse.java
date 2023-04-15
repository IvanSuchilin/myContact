package com.example.myContact.exceptions.response;

public class ErrorResponse {
    private final String error;

    public ErrorResponse(String error) {

        this.error = error;
    }

    public String getError() {
        return error;
    }
}

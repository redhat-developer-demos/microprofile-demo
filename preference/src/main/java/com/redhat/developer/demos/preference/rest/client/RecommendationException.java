package com.redhat.developer.demos.preference.rest.client;

import javax.ws.rs.*;

public class RecommendationException extends WebApplicationException {

    private int status;

    public RecommendationException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}

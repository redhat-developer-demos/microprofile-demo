package com.redhat.developer.demos.customer.rest.client;

import javax.ws.rs.*;

public interface PreferenceService {

    @GET
    public String getPreference() throws PreferenceException;

}

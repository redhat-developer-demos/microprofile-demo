package com.rafabene.microprofile.client;

import javax.ws.rs.*;

@Path("/api")
public interface PreferenceService {

    @GET
    public String getPreference() throws PreferenceException;

}

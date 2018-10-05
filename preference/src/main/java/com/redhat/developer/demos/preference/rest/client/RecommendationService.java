package com.redhat.developer.demos.preference.rest.client;

import javax.ws.rs.*;

@Path("/")
public interface RecommendationService {

    @GET
    public String getRecommendation() throws RecommendationException;

}

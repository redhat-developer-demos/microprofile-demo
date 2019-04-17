package com.rafabene.microprofile.client;

import javax.ws.rs.*;

@Path("/api")
public interface RecommendationService {

    @GET
    public String getRecommendation() throws RecommendationException;

}

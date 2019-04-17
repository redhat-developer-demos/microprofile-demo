package com.rafabene.microprofile.client;

import org.eclipse.microprofile.rest.client.ext.*;

import javax.ws.rs.core.*;

public class RecommendationExceptionWrapper implements ResponseExceptionMapper<RecommendationException> {


    @Override
    public RecommendationException toThrowable(Response response) {
        return new RecommendationException(response.getStatus(), response.readEntity(String.class));
    }
}

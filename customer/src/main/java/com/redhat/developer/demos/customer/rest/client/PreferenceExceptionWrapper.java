package com.redhat.developer.demos.customer.rest.client;

import org.eclipse.microprofile.rest.client.ext.*;

import javax.ws.rs.core.*;

public class PreferenceExceptionWrapper implements ResponseExceptionMapper<PreferenceException> {


    @Override
    public PreferenceException toThrowable(Response response) {
        return new PreferenceException(response.getStatus(), response.readEntity(String.class));
    }
}

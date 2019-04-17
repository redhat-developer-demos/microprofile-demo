package com.rafabene.microprofile.health;

import com.rafabene.microprofile.client.*;
import org.apache.http.util.*;
import org.eclipse.microprofile.config.inject.*;
import org.eclipse.microprofile.health.*;
import org.eclipse.microprofile.rest.client.*;

import javax.enterprise.context.*;
import javax.inject.*;
import java.net.*;


@Health
@ApplicationScoped
public class PreferenceHealthCheck implements HealthCheck {

    @Inject
    @ConfigProperty(name = "preference.api.url",
            defaultValue = "http://localhost:8081/")
    private String preferenceURL;


    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder response = HealthCheckResponse.named("preferencesAvailability");
        try {
            URL url = new URL(preferenceURL);
            PreferenceService preferenceService = RestClientBuilder
                    .newBuilder()
                    .baseUrl(url)
                    .register(PreferenceExceptionWrapper.class)
                    .build(PreferenceService.class);
            preferenceService.getPreference();
            return response.up().build();
        } catch (Exception ex) {
            return response.down().withData("cause", ex.getCause().getMessage()).build();
        }

    }
}

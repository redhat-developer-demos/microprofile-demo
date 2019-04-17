package com.rafabene.microprofile;


import com.rafabene.microprofile.client.*;
import org.eclipse.microprofile.config.inject.*;
import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.metrics.annotation.*;
import org.eclipse.microprofile.openapi.annotations.*;
import org.eclipse.microprofile.openapi.annotations.tags.*;
import org.eclipse.microprofile.opentracing.*;
import org.eclipse.microprofile.rest.client.*;
import org.slf4j.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.*;

import static java.lang.String.format;

@ApplicationScoped
@Path("/api")
@Tag(name = "Preference Service", description = "Get Preference and Recommendation")
public class PreferenceEndpoint {


    @Inject
    @ConfigProperty(name = "recommendation.api.url",
            defaultValue = "http://localhost:8082/")
    private String recommendationURL;

    private static final String RESPONSE_STRING_FORMAT = "preference => %s\n";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GET
    @Produces("text/plain")
    @Traced
    @Retry(maxRetries = 5)
    @Timeout
    @Fallback(fallbackMethod = "fallback")
    @Operation(description = "Get Preference and Recommendation")
    public Response doGet() throws MalformedURLException {
        URL url = new URL(recommendationURL);
        RecommendationService recommendationService = RestClientBuilder
                .newBuilder()
                .baseUrl(url)
                .register(RecommendationExceptionWrapper.class)
                .build(RecommendationService.class);
            return Response.ok(format(RESPONSE_STRING_FORMAT, recommendationService.getRecommendation())).build();
    }

    public Response fallback(){
        logger.info("Fallback method invoked");
        return Response.ok("FallBack method").build();
    }

}

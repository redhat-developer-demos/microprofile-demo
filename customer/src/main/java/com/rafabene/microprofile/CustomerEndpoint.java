package com.rafabene.microprofile;

import com.rafabene.microprofile.client.*;
import io.quarkus.runtime.annotations.*;
import org.apache.http.util.*;
import org.eclipse.microprofile.config.inject.*;
import org.eclipse.microprofile.openapi.annotations.*;
import org.eclipse.microprofile.openapi.annotations.media.*;
import org.eclipse.microprofile.openapi.annotations.responses.*;
import org.eclipse.microprofile.openapi.annotations.tags.*;
import org.eclipse.microprofile.opentracing.*;
import org.eclipse.microprofile.rest.client.*;
import org.slf4j.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.*;

import static java.lang.String.format;

@ApplicationScoped
@Path("/api")
@Tag(name = "Customer Service", description = "Get Customer, Preference and Recommendation")
public class CustomerEndpoint {

	@Inject
	@ConfigProperty(name = "preference.api.url",
			defaultValue = "http://localhost:8081/")
	private String preferenceURL;

	private static final String RESPONSE_STRING_FORMAT = "customer => %s\n";

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@GET
	@Traced
    @Operation(description = "Get Customer, Preference and Recommendation")
	public Response doGet() throws MalformedURLException {
		URL url = new URL(preferenceURL);
		PreferenceService preferenceService = RestClientBuilder
				.newBuilder()
				.baseUrl(url)
				.register(PreferenceExceptionWrapper.class)
				.build(PreferenceService.class);
		try {
			return Response.ok(format(RESPONSE_STRING_FORMAT, preferenceService.getPreference())).build();
		} catch (PreferenceException e) {
			return Response
					.status(Response.Status.SERVICE_UNAVAILABLE)
					.entity(format(RESPONSE_STRING_FORMAT,
							format("Error: %d - %s", e.getStatus(), e.getMessage()))
					)
					.build();
		} catch (Exception e) {
			return Response
					.status(Response.Status.SERVICE_UNAVAILABLE)
					.entity(format(RESPONSE_STRING_FORMAT,
							format("Error: %s", e.getCause()))
					)
					.build();

		}
	}
}

package com.redhat.developer.demos.customer.rest;

import com.redhat.developer.demos.customer.rest.client.*;

import org.apache.commons.lang.exception.*;
import org.eclipse.microprofile.config.inject.*;
import org.eclipse.microprofile.rest.client.*;
import org.slf4j.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.net.*;

import static java.lang.String.format;

@ApplicationScoped
@Path("/")
public class CustomerEndpoint {
	@Inject
	@ConfigProperty(name = "preference.api.url",
			defaultValue = "http://localhost:8180/")
	private String recommendationURL;

	private static final String RESPONSE_STRING_FORMAT = "customer => %s\n";

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@GET
	@Produces("text/plain")
	public Response doGet() throws MalformedURLException {
		URL url = new URL(recommendationURL);
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
							format("Error: %s", ExceptionUtils.getRootCause(e)))
					)
					.build();

		}
	}
}

package com.rafabene.microprofile;

import org.eclipse.microprofile.openapi.annotations.*;
import org.eclipse.microprofile.openapi.annotations.info.*;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(info = @Info(
        title = "Recommendation Service",
        description = "This is the Recommendation microservice",
        version = "1.0.0-SNAPSHOT",
        contact = @Contact(
                name = "Rafael Benevides",
                email = "rafabene@gmail.com",
                url = "http://rafabene.com"
        )
))
public class RestApplication extends Application {

}

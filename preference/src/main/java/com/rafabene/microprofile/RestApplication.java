package com.rafabene.microprofile;


import org.eclipse.microprofile.openapi.annotations.*;
import org.eclipse.microprofile.openapi.annotations.info.*;

import javax.ws.rs.core.*;

@OpenAPIDefinition(info = @Info(
        title = "Preference Service",
        description = "This is the Preference microservice",
        version = "1.0.0-SNAPSHOT",
        contact = @Contact(
                name = "Rafael Benevides",
                email = "rafabene@gmail.com",
                url = "http://rafabene.com"
        )
))
public class RestApplication extends Application {

}

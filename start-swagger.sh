wget -c http://repo2.maven.org/maven2/io/thorntail/servers/swagger-ui/2.3.0.Final/swagger-ui-2.3.0.Final-thorntail.jar .
open http://localhost:8380/swagger-ui
java -Dswarm.http.port=8380 -jar swagger-ui-2.3.0.Final-thorntail.jar



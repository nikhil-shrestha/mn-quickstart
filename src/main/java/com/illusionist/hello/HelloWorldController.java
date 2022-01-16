package com.illusionist.hello;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/hello")
public class HelloWorldController {

  private static final Logger LOG = LoggerFactory.getLogger(HelloWorldController.class);

  private final MyService service;
  private final String helloFromConfig;

  public HelloWorldController(MyService service, @Property(name = "hello.world.message") String helloFromConfig) {
    this.service = service;
    this.helloFromConfig = helloFromConfig;
  }

  @Get(produces = MediaType.TEXT_PLAIN)
  public String helloWorld(){
    LOG.debug("Called the helloWorld API");
    return service.helloFromService();
  }

  @Get(uri="/config", produces = MediaType.TEXT_PLAIN)
  public String helloFromConfig(){
    LOG.debug("Return Hello from Config Message: {}", helloFromConfig);
    return helloFromConfig;
  }


}

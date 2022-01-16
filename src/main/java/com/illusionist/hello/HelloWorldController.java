package com.illusionist.hello;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/hello")
public class HelloWorldController {

  private static final Logger LOG = LoggerFactory.getLogger(HelloWorldController.class);

  private final MyService service;

  public HelloWorldController(MyService service) {
    this.service = service;
  }

  @Get(produces = MediaType.TEXT_PLAIN)
  public String helloWorld(){
    LOG.debug("Called the helloWorld API");
    return service.helloFromService();
  }


}

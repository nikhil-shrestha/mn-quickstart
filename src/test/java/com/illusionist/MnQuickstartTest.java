package com.illusionist;

import com.fasterxml.jackson.databind.JsonNode;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class HelloWorldControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void helloWorldEndpointRespondsWithProperContent(){
        var response = client.toBlocking().retrieve("/hello");

        assertEquals("Hello from Service!", response);
    }

    @Test
    void helloWorldEndpointRespondsWithProperStatusCodeAndContent(){
        var response = client.toBlocking().exchange("/hello", String.class);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Hello from Service!", response.getBody().get());
    }

    @Test
    void helloFromConfigEndpointReturnsMessageFromConfigFile() {
        var response = client.toBlocking().exchange("/hello/config", String.class);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Hello from application.yml", response.getBody().get());
    }

    @Test
    void helloFromTranslationEndpointReturnsContentFromConfigFile() {
        var response = client.toBlocking().exchange("/hello/translation", JsonNode.class);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("{\"de\":\"Hallo Walt\",\"en\":\"Hello World\"}", response.getBody().get().toString());
    }
}

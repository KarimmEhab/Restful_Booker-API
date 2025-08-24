package com.restfulBooker.positiveScenarios;

import baseTest.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HealthCheck extends BaseTest {

    @Test
    public void healthCheckTest(){
        logger.info("Starting Ping Health Check test");

        given()
        .when()
               .get("/ping")
        .then().assertThat().statusCode(201);

        logger.info("Ping endpoint is healthy and returned 201 Created");

    }
}

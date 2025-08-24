package com.restfulBooker.negativeScenarios;

import baseTest.BaseTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class InvalidAuthToken extends BaseTest {
    @Test
    public void wongUsernameTest(){
        logger.info("Starting Invalid Auth Test - Wrong Username");

            Map<String, String> authBody = new HashMap<>();
            authBody.put("username", "wrong");
            authBody.put("password", "password123");

            given()
                    .header("Content-Type", "application/json")
                    .body(authBody)
            .when()
                    .post("/auth")
            .then()
                    .log().all()
                    .assertThat().statusCode(200) // API spec says it still returns 200
                    .assertThat().body("reason", equalTo("Bad credentials"));

        logger.info("Invalid Auth (wrong username) verified successfully.");
    }

    @Test
    public void wrongPasswordTest() {
        logger.info("Starting Invalid Auth Test - Wrong Password");

        Map<String, String> authBody = new HashMap<>();
        authBody.put("username", "admin");
        authBody.put("password", "wrongPass");

        given()
                .header("Content-Type", "application/json")
                .body(authBody)
                .when()
                .post("/auth")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("reason", equalTo("Bad credentials"));

        logger.info("Invalid Auth (wrong password) verified successfully.");
    }
}

package com.restfulBooker.positiveScenarios;

import baseTest.BaseTest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthToken extends BaseTest {
    public static String token;

    @Test
    public void generateToken() {
        logger.info("Create Auth Token test");

        Map<String, String> authBody = new HashMap<>();
        authBody.put("username", "admin");
        authBody.put("password", "password123");

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(authBody)
                .when()
                        .post("/auth")
                .then()
                        .assertThat().statusCode(200)
                        .extract().response();

         token = response.jsonPath().getString("token");

        logger.info("Generated Token: {}", token);
//
//        try {
//            Files.writeString(Paths.get("token.txt"), token);
//        } catch (Exception e) {
//            throw new RuntimeException("Error saving token", e);
//        }
//    }
//
//    public static String getSavedToken() {
//        try {
//            return Files.readString(Paths.get("token.txt")).trim();
//        } catch (Exception e) {
//            throw new RuntimeException("Token file not found, please generate token first", e);
//        }
//    }

    }
}

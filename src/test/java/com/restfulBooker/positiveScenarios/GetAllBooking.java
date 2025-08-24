package com.restfulBooker.positiveScenarios;

import baseTest.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GetAllBooking extends BaseTest {

    @Test
    public void getAllBookingIDsTests(){
        logger.info("Starting Get All Booking IDs test");

        // Step 1: Send GET request & extract response
        Response response =
                given()
                .when()
                        .get("/booking")
                .then()
                        .log().all()
                        .assertThat().statusCode(200)
                        .assertThat().body("bookingid",is(not(empty())))
                        .extract().response();

        logger.info("Response size: {}", response.jsonPath().getList("bookingid").size());
    }
}

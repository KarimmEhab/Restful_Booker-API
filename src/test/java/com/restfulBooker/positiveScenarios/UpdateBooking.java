package com.restfulBooker.positiveScenarios;

import baseTest.BaseTest;
import data.BookingData;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UpdateBooking extends BaseTest {

    @Test(dependsOnMethods = {
            "com.restfulBooker.positiveScenarios.AuthToken.generateToken",
            "com.restfulBooker.positiveScenarios.CreateBooking.createBooking"
    })
    public void updateBookingTest(){
        logger.info("Update Booking test Using PUT");

//        AuthToken auth = new AuthToken();
//        auth.generateToken();
//
//        CreateBooking create = new CreateBooking();
//        create.createBooking();

        // Updates Booking body
        Map<String, Object> UpdatedBookingBody = new HashMap<>(BookingData.bookingBody());
        UpdatedBookingBody.put("firstname", "Hazem");
        UpdatedBookingBody.put("totalprice", 270);
        UpdatedBookingBody.put("depositpaid", false);

        // Send PUT request
        Response createResponse =
                given()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .header("Cookie","token=" + AuthToken.token)
                        .body(UpdatedBookingBody)
                        .pathParams("bookingId", CreateBooking.bookingId)
                .when()
                        .put("/booking/{bookingId}")
                .then()
                        .log().all()
                        .assertThat().statusCode(200)
                        .assertThat().body("firstname",equalTo("Hazem"))
                        .assertThat().body("totalprice",equalTo(270))
                        .assertThat().body("depositpaid", equalTo(false))
                        .extract().response();

        logger.info("Token: {}", AuthToken.token);
        logger.info("Booking ID: {}", CreateBooking.bookingId);
        logger.info("Booking updated successfully.");

    }
}

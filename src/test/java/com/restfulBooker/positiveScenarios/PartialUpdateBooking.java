package com.restfulBooker.positiveScenarios;

import baseTest.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PartialUpdateBooking extends BaseTest {

    @Test(dependsOnMethods = {
            "com.restfulBooker.positiveScenarios.AuthToken.generateToken",
            "com.restfulBooker.positiveScenarios.CreateBooking.createBooking"
    })
    public void partialUpdateBookingTest(){
        logger.info("Update Booking test Using PATCH");

//        AuthToken auth = new AuthToken();
//        auth.generateToken();

//        CreateBooking create = new CreateBooking();
//        create.createBooking();

        // Booking Dates
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2025-09-25");
        bookingDates.put("checkout", "2025-09-28");

        // Partial Updates Booking body
        Map<String, Object> UpdatedBookingBody = new HashMap<>();
        UpdatedBookingBody.put("firstname", "Karim");
        UpdatedBookingBody.put("bookingdates", bookingDates);
        UpdatedBookingBody.put("depositpaid", true);

        // Send PUT request
        Response createResponse =
                given()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .header("Cookie","token=" + AuthToken.token)
                        .body(UpdatedBookingBody)
                        .pathParams("bookingId", CreateBooking.bookingId)
                .when()
                        .patch("/booking/{bookingId}")
                .then()
                        .log().all()
                        .assertThat().statusCode(200)
                        .assertThat().body("firstname",equalTo("Karim"))
                        .assertThat().body("bookingdates.checkin",equalTo("2025-09-25"))
                        .assertThat().body("bookingdates.checkout",equalTo("2025-09-28"))
                        .assertThat().body("depositpaid", equalTo(true))
                        .extract().response();

        logger.info("Booking partially updated successfully.");

    }
}

package com.restfulBooker.positiveScenarios;

import baseTest.BaseTest;
import data.BookingData;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class CreateBooking extends BaseTest{
    public static int bookingId;

    @Test
    public void createBooking() {
        logger.info("Create Booking test");

        // Send POST request
        Response createResponse =
                given()
                        .header("Content-Type", "application/json")
                        .body(BookingData.bookingBody())
                .when()
                        .post("/booking")
                .then()
                        .log().all()
                        .assertThat().statusCode(200)
                        .assertThat().body("bookingid",notNullValue())
                        .assertThat().body("booking.firstname",equalTo("Karim"))
                        .extract().response();

        bookingId = createResponse.jsonPath().getInt("bookingid");
        logger.info("Created Booking sucssefully with ID: {}", bookingId);

    }
}

package com.restfulBooker.positiveScenarios;

import baseTest.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DeleteBooking extends BaseTest {

    @Test(dependsOnMethods = {
            "com.restfulBooker.positiveScenarios.AuthToken.generateToken",
            "com.restfulBooker.positiveScenarios.CreateBooking.createBooking"
    })
    public void deleteBookingTest() {
        logger.info("Delete Booking test");

        given()
                .header("Content-Type", "application/json")
                .header("Cookie","token=" + AuthToken.token)
                .pathParams("bookingId", CreateBooking.bookingId)
        .when()
                .delete("/booking/{bookingId}")
        .then()
                .log().all()
                .assertThat().statusCode(201)
                .assertThat().body(equalTo("Created"));

        logger.info("Booking deleted successfully.");
    }

}

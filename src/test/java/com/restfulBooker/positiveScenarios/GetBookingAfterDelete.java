package com.restfulBooker.positiveScenarios;

import baseTest.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.restfulBooker.positiveScenarios.CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetBookingAfterDelete extends BaseTest {

    @Test
    void getBookingAfterDelete(){
        logger.info("Get Booking By ID After Delete test");

//        CreateBooking create = new CreateBooking();
//        create.createBooking();

        Response response =
                given()
                        .pathParams("bookingId", bookingId)
                 .when()
                        .get("/booking/{bookingId}")
                 .then()
                        .log().all()
                        .assertThat().statusCode(404)
                        .assertThat().body(equalTo("Not Found"))
                        .extract().response();
        logger.info("Created Booking ID: {}", bookingId);


    }
}

package com.restfulBooker.positiveScenarios;

import baseTest.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetBookingById extends BaseTest {

    @Test(dependsOnMethods = "com.restfulBooker.positiveScenarios.CreateBooking.createBooking")
    void getBookingByIdTest(){
        logger.info("Get Booking By ID test");

//        CreateBooking create = new CreateBooking();
//        create.createBooking();

        Response response =
                given()
                        .pathParams("bookingId", CreateBooking.bookingId)
                .when()
                        .get("/booking/{bookingId}")
                .then()
                        .log().all()
                        .assertThat().statusCode(200)
                        .assertThat().body("firstname",equalTo("Karim"))
                        .assertThat().body("totalprice",equalTo(150))
                        .extract().response();

        logger.info("Get Booking By ID sucssefully");

    }
}

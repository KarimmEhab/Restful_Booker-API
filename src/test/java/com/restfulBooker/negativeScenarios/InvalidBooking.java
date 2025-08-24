package com.restfulBooker.negativeScenarios;

import baseTest.BaseTest;
import com.restfulBooker.positiveScenarios.AuthToken;
import data.BookingData;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class InvalidBooking extends BaseTest {

    @Test
    public void createBookingWithWrongURL() {
        logger.info("Starting test: Create Booking with invalid URL");

//        Map<String, Object> booking = new HashMap<>(BookingData.bookingBody());
//        booking.put("firstname", "Karim");
//        booking.put("lastname", "Ehab");

        given()
                .header("Content-Type", "application/json")
                .body(BookingData.bookingBody())
        .when()
                .post("/invalidURL")
        .then()
                .log().all()
                .assertThat().statusCode(404)
                .assertThat().body(equalTo("Not Found"));

        logger.info("Test completed: Create Booking with invalid URL");
    }

    @Test
    public void createBookingWithMissingData() {
        logger.info("Starting test: Create Booking with missing data");

        Map<String, Object> booking = new HashMap<>(BookingData.bookingBody());
        booking.remove("firstname");

        given()
                .header("Content-Type", "application/json")
                .body(booking)
        .when()
                .post("/booking")
        .then()
                .log().all()
                .assertThat().statusCode(500)
                .assertThat().body(containsString("Error"));

        logger.info("Test completed: Create Booking with missing data");
    }

    @Test
    public void createBookingWithWrongData() {
        logger.info("Starting test: Trying to Create Booking with wrong data");

        Map<String, Object> booking = new HashMap<>(BookingData.bookingBody());
        booking.replace("firstname", 1234);

        given()
                .header("Content-Type", "application/json")
                .body(booking)
        .when()
                .post("/booking")
        .then()
                .log().all()
                .assertThat().statusCode(500)
                .assertThat().body(containsString("Error"));

        logger.info("Test completed: Trying to Create Booking with wrong data");
    }

    @Test
    public void createBookingWithWrongMethod() {
        logger.info("Starting test: Trying to Create Booking with wrong HTTP method");

        given()
                .header("Content-Type", "application/json")
                .body(BookingData.bookingBody())
        .when()
                .put("/booking") // PUT method instead of POST
        .then()
                .log().all()
                .assertThat().statusCode(404)
                .assertThat().body(equalTo("Not Found"));

        logger.info("Test completed: Trying to Create Booking with wrong HTTP method");
    }

    @Test
    public void updateBookingWithWrongToken() {
        logger.info("Starting test: Update Booking with invalid token");

        Map<String, Object> body = new HashMap<>(BookingData.bookingBody());
        body.put("firstname", "Hazem");

        given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=invalidToken")
                .pathParam("bookingId", 1)
                .body(body)
        .when()
                .put("/booking/{bookingId}")
        .then()
                .log().all()
                .assertThat().statusCode(403)
                .assertThat().body(equalTo("Forbidden"));

        logger.info("Test completed: Update Booking with invalid token");
    }

    @Test
    public void updateBookingWithWrongID() {
        logger.info("Starting test: Update Booking with invalid booking ID");

        AuthToken auth = new AuthToken();
        auth.generateToken();

        Map<String, Object> body = new HashMap<>(BookingData.bookingBody());
        body.put("firstname", "Hazem");

        given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + AuthToken.token)
                .pathParam("bookingId", 999999)
                .body(body)
        .when()
                .put("/booking/{bookingId}")
        .then()
                .log().all()
                .assertThat().statusCode(405)
                .assertThat().body(equalTo("Method Not Allowed")); //  Server does not allow PUT method with wrong ID

        logger.info("Test completed: Update Booking with invalid booking ID");
    }

    @Test
    public void getBookingWithWrongID() {
        logger.info("Starting test: Get booking with invalid ID");

        given()
                .header("Accept", "application/json")
                .pathParam("bookingId", 999999)
        .when()
                .get("/booking/{bookingId}")
        .then()
                .log().all()
                .assertThat().statusCode(404)
                .assertThat().body(equalTo("Not Found"));  // Server does not found the ID

        logger.info("Test completed: Get booking with invalid ID");
    }

    @Test
    public void deleteBookingWithoutToken() {
        logger.info("Starting test: Delete booking without Auth token");

        given()
                .header("Content-Type", "application/json")
                .pathParam("bookingId", 1)
        .when()
                .delete("/booking/{bookingId}")
        .then()
                .log().all()
                .assertThat().statusCode(403)
                .assertThat().body(equalTo("Forbidden"));

        logger.info("Test completed: Delete booking without Auth token");
    }

}

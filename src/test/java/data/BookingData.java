package data;

import java.util.HashMap;
import java.util.Map;

public class BookingData {

    public static Map<String, Object> bookingDates() {
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2025-08-25");
        bookingDates.put("checkout", "2025-08-28");
        return bookingDates;
    }

    public static Map<String, Object> bookingBody() {
        Map<String, Object> bookingBody = new HashMap<>();
        bookingBody.put("firstname", "Karim");
        bookingBody.put("lastname", "Ehab");
        bookingBody.put("totalprice", 150);
        bookingBody.put("depositpaid", true);
        bookingBody.put("bookingdates", bookingDates());
        bookingBody.put("additionalneeds", "Breakfast");
        return bookingBody;
    }
}

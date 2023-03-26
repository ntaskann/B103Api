package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import util.ObjectMapperUtils;

import static herokuapp_smoketest.S1Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static util.AuthenticationHerOkuApp.generateToken;

public class S2Put extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/{id}

            {
             "firstname" : "Veli",
             "lastname" : "San",
             "totalprice" : 111,
             "depositpaid" : true,
             "bookingdates" : {
                 "checkin" : "2018-01-01",
                 "checkout" : "2019-01-01"
             },
             "additionalneeds" : "Breakfast"
            }

        When
            Send the request

        Then
            Status Code should be 200

        And
            {
                "firstname": "Veli",
                "lastname": "San",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }


     */

    @Test
    public void put01() {
        //Set the url
        spec.pathParams("first", "booking", "second", 6567);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");

        BookingPojo expectedData = new BookingPojo("Veli", "San", 111, true, bookingDatesPojo, "Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().
                spec(spec).
                body(expectedData).
                put("/{first}/{second}");
        response.prettyPrint();

        //Do assertion

        BookingPojo actualData = ObjectMapperUtils.convertJsontoJava(response.asString(), BookingPojo.class);

        assertEquals(200, response.statusCode());

        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());


    }


}
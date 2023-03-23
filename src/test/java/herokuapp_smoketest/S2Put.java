package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static herokuapp_smoketest.S1Post.bookingId;
import static io.restassured.RestAssured.given;
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
        spec.pathParams("first", "booking", "second", 8939);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");

        BookingPojo expectedData = new BookingPojo("Veli", "San", 111, true, bookingDatesPojo, "Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().
                spec(spec).
                header("Cookie","token="+generateToken()).
                body(expectedData).
                put("/{first}/{second}");
        response.prettyPrint();

        //Do assertion

    }


}

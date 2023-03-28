package homework.herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetBooking extends HerOkuAppBaseUrl {

    /*
         Given
            https://restful-booker.herokuapp.com/booking/:id

         When
            Send the get request

         Then
            Status code should be 404

         And
            Response body is like "Not Found"
     */
    @Test
    public void get() {
        //Set the url
        spec.pathParams("first", "booking", "second", PostBooking.bookingId);

        //Set the expected data
        String expectedData = "Not Found";

        //Send the request
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(404, response.statusCode());
        assertEquals(expectedData, response.asString());
    }


}

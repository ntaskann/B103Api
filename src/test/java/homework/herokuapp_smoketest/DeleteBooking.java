package homework.herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class DeleteBooking extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/1

        When
            Send the delete request

        Then
            Status code should be 201

        And
            Body should be Created

     */

    @Test
    public void delete01() {
        //Set the url
        spec.pathParams("first", "booking", "second", PostBooking.bookingId);

        //Set the expected data
        String expectedData = "Created";

        //Send the request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        assertEquals(201, response.statusCode());
        assertEquals(expectedData, response.asString());
    }
}

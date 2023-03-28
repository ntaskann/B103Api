package homework;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get04 extends HerOkuAppBaseUrl {


    /*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Almedin&lastname=Alikadic
        When
            User sends get request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Almedin" and lastname is "Alikadic"

     */
    @Test
    public void get04() {
        //Set the Url
        spec.pathParam("first", "booking").
                queryParams("firstname", "Josh", "lastname", "Allen");

        //Set the expected data

        //Set the request and get the response
        Response response = given().spec(spec).when().get("{first}");
        response.prettyPrint();

        //Do Assertion
        assertEquals(200, response.statusCode());
        assertTrue(response.asString().contains("bookingid"));

    }
}

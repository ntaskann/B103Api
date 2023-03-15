package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a GET request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Sally" and last name is "Brown"
            (Data içerisinde firstname değeri "Sally", lastname değeri "Brown" olan biri olmalı)
     */

    @Test
    public void get05() {
        //https://restful-booker.herokuapp.com/booking?firstname=Sally&lastname=Brown

        //Set the Url
        spec.
                pathParam("first", "booking").
                queryParams("firstname", "Sally", "lastname", "Brown");


        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.
                then().
                statusCode(200);

        assertTrue(response.asString().contains("bookingid"));



    }


}

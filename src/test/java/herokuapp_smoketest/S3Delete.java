package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import util.AuthenticationHerOkuApp;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static util.AuthenticationHerOkuApp.generateToken;

public class S3Delete extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/1881
        When
            Send delete request
        And
            Body should be created


     */


    @Test
    public void delete01() {

        //Set the url
        spec.pathParams("first", "booking", "second", 1810);

        //Set the expected data
        String expectedData = "Created";

        //Send the request and get the response
        Response response =
                given().
                        spec(spec).
                        delete("/{first}/{second}");
        response.prettyPrint();


        //Do assertion

        assertEquals(201, response.statusCode());

        assertEquals(expectedData, response.asString());

    }

}

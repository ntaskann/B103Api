package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {

      /*
        Given
            https://restful-booker.herokuapp.com/booking/55
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    @Test
    public void get01() {

        //i)   Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/54";

        //ii)  Set the expected data

        //iii) Send the request and get the response
        Response response = given().when().get("https://restful-booker.herokuapp.com/booking/53");
        response.prettyPrint();

        //iv)  Do assertion
        response.
                then().
                statusCode(200).//beklenen kod 200 onu dogruluyorum.
                contentType("application/json").
                statusLine("HTTP/1.1 200 OK");

    }


}
package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import org.codehaus.jackson.map.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05ObjectMapper_Map extends JsonPlaceHolderBaseUrl {

    /*
     Given
       1) https://jsonplaceholder.typicode.com/todos
       2) {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
           }


        I send POST Request to the Url
    Then
        Status code is 201
    And
        response body is like {
                                "userId": 55,
                                "title": "Tidy your room",
                                "completed": false,
                                "id": 201
                                }
 */

    @Test
    public void post05() throws IOException {

        //Set the url
        spec.pathParam("first", "todos");

        //Set the expeceted data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataMethod(55, "Tidy your room", false);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).post("/{first}");
        response.prettyPrint();

        //Do assertion
        Map<String, Object> actualData = new ObjectMapper().readValue(response.asString(), HashMap.class);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));

    }
}

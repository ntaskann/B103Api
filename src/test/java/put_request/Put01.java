package put_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {

    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									    "id": 198
									   }
     */

    @Test
    public void put01() {

        //set the url
        spec.pathParams("first", "todos", "second", 198);

        //set the expected data
        //once bos bir map olusturduk,map e body bilgilerini koyduk,
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 21.0);
        expectedData.put("title", "Wash the dishes");
        expectedData.put("completed", false);
        System.out.println("expectedData = " + expectedData);

        //set the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).when().body(expectedData).put("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));


    }

    @Test//dimamic yontem
    public void put01b() {

        //set the url
        spec.pathParams("first", "todos", "second", 198);

        //set the expected data
        //once bos bir map olusturduk,map e body bilgilerini koyduk,
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataMethod(21, "Wash the dishes", false);


        //set the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).when().body(expectedData).put("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));


    }
}

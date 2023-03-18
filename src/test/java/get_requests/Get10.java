package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get10 extends GoRestBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users/171923
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            /*
 {
    "meta": null,
    "data": {
        "id": 171923,
        "name": "Karthik Naik",
        "email": "karthik_naik@treutel-welch.co",
        "gender": "female",
        "status": "active"
    }
}
}
     */

    @Test
    public void get10() {

        //Set the url
        spec.pathParams("first", "users", "second", 171923);

        //Set the expected data
        GoRestTestData obj = new GoRestTestData();
        Map<String, String> dataMap = obj.dataMapMethod("Karthik Naik", "karthik_naik@treutel-welch.co", "female", "active");

        Map<String, Object> expectedData = obj.expectedDataMapMethod(null, dataMap);
        System.out.println("expectedData = " + expectedData);


        //Send the request and get the responce
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200, response.statusCode());

        assertEquals(expectedData.get("meta"), actualData.get("meta"));
        assertEquals(dataMap.get("name"), ((Map) actualData.get("data")).get("name"));
        assertEquals(dataMap.get("email"), ((Map) actualData.get("data")).get("email"));
        assertEquals(dataMap.get("gender"), ((Map) actualData.get("data")).get("gender"));
        assertEquals(dataMap.get("status"), ((Map) actualData.get("data")).get("status"));


    }


}

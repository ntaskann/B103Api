package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13 extends GoRestBaseUrl {


    /*
            Given
                https://gorest.co.in/public/v1/users/274256
            When
                User send GET Request to the URL
            Then
                Status Code should be 200
            And
                Response body should be like
              {
                 "meta": null,
                 "data": {
                     "id": 274256,
                     "name": "Deveshwar Pandey",
                     "email": "pandey_deveshwar@raynor.org",
                     "gender": "male",
                     "status":  "active"
    }
}
        */
    @Test
    public void get13() {

        //Set the url
        spec.pathParams("first", "users", "second", 274256);

        //Set the expected data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo("Deveshwar Pandey", "pandey_deveshwar@raynor.org", "male", "active");
        GoRestPojo expectedData = new GoRestPojo(null, goRestDataPojo);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        GoRestPojo actualData = response.as(GoRestPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(goRestDataPojo.getName(),actualData.getData().getName());
        assertEquals(goRestDataPojo.getEmail(),actualData.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(),actualData.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(),actualData.getData().getStatus());

    }
}

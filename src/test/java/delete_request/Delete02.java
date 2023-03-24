package delete_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiDeleteBodyPojo;
import pojos.DummyRestApiResponseBodyPojo;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete02 extends DummyRestApiBaseUrl {

     /*
        Given
            URL: https://dummy.restapiexample.com/api/v1/delete/2
        When
            HTTP Request Method: DELETE Request
        Then
             Status code is 200
        And
            "status" is "success"
        And
             "data" is "2"
        And
             "message" is "Successfully! Record has been deleted"

  */

    @Test
    public void delete02() {

        //Set the url
        spec.pathParams("first", "delete", "second", 2);

        //Set the expected data

        DummyRestApiDeleteBodyPojo expectedData = new DummyRestApiDeleteBodyPojo("success", "2", "Successfully! Record has been deleted");

        //Send the request and get the response
        Response response = given().spec(spec).delete("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        DummyRestApiDeleteBodyPojo actualData = ObjectMapperUtils.convertJsontoJava(response.asString(), DummyRestApiDeleteBodyPojo.class);

        assertEquals(200, response.statusCode());

        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getData(),actualData.getData());
        assertEquals(expectedData.getMessage(),actualData.getMessage());


    }
}

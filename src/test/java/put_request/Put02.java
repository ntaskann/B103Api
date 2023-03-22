package put_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponseBodyPojo;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02 extends DummyRestApiBaseUrl {

    /*
       URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */

    @Test
    public void put02() {

        //Set the url
        spec.pathParams("first", "update", "second", 21);

        //Set the expected data
        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("Ali Can", 111111, 23, "Perfect image");
        System.out.println("expectedData = " + expectedData);
        DummyRestApiResponseBodyPojo expectedBodyPojo = new DummyRestApiResponseBodyPojo("success", expectedData, "Successfully! Record has been updated.");
        // Note : expectedBodyPojo objesini actualData data ile karsilastirarak assertion yapmak icin olusturduk

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).put("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        DummyRestApiResponseBodyPojo actualData = ObjectMapperUtils.convertJsontoJava(response.asString(), DummyRestApiResponseBodyPojo.class);

        assertEquals(200, response.statusCode());

        assertEquals(expectedBodyPojo.getStatus(), actualData.getStatus());
        assertEquals(expectedBodyPojo.getMessage(), actualData.getMessage());

        assertEquals(expectedData.getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(), actualData.getData().getProfile_image());




    }


}

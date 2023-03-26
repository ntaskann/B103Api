package post_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponseBodyPojo;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06 extends DummyRestApiBaseUrl {

    /*
       Given
       URL: https://dummy.restapiexample.com/api/v1/create
       Request body:
                     {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image",
                        "id": 4891
                     }

       When
       HTTP Request Method: POST Request

       Then

       Status code is 200

       And
              Response body should be like the following
                 {
                     "status": "success",
                     "data": {
                         "employee_name": "Tom Hanks",
                         "employee_salary": 111111,
                         "employee_age": 23,
                         "profile_image": "Perfect image",
                         "id": 4891
                     },
                     "message": "Successfully! Record has been added."
                 }
     */

    @Test
    public void post06() {
        //Set the url
        spec.pathParam("first", "create");

        //Set the expected data
        DummyRestApiDataPojo obj = new DummyRestApiDataPojo("Tom Hanks", 111111, 23, "Perfect image");
        DummyRestApiResponseBodyPojo expectedData = new DummyRestApiResponseBodyPojo("success", obj, "Successfully! Record has been added.");
        System.out.println("expectedData = " + expectedData);

        /*
            Not: Oncelik olarak gondersigim datanin dogrulamasını yapmaliyim. Eger bizden ozel olarak response body nin tamaminin assert i
                istenmiyorsa gonderilen datanin dogrulanmasi yeterli olabilir.
         */

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).post("/{first}");
        response.prettyPrint();

        //Do assertion
        DummyRestApiResponseBodyPojo actualData = ObjectMapperUtils.convertJsontoJava(response.asString(), DummyRestApiResponseBodyPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getStatus(), actualData.getStatus());
        assertEquals(obj.getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(obj.getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(obj.getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(obj.getProfile_image(), actualData.getData().getProfile_image());
        assertEquals(expectedData.getMessage(), actualData.getMessage());

    }
}

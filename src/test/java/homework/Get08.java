package homework;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get08 extends DummyRestApiBaseUrl {
     /*
     Given
        URL: https://dummy.restapiexample.com/api/v1/employees
     When
        HTTP Request Method: GET Request
     Then
        Status code is 200
     And
        There are 24 employees
        (HamcrestMatcher kullanarak 24 employees olduğunu doğrulayınız)
     And
        "Tiger Nixon" and "Garrett Winters" are among the employees
        (HamcrestMatcher kullanarak "Tiger Nixon" ve "Garrett Winters"'ın employees arasında olduğunu doğrulayınız' )
     And
        The greatest age is 66
        (En büyük yaşın 66 olduğunu doğrulayınız)
     And
        The name of the lowest age is "Tatyana Fitzpatrick"
        (En düşük yaşın isminin Tatyana Fitzpatrick olduğunu doğrulayınız)
     And
        Total salary of all employees is 6,644,770
        (Tüm employees salary toplamının  6,644,770 olduğunu doğrulayınız)

    */

    @Test
    public void get08() {
        //Set the url
        spec.pathParam("first", "employees");

        //Set the expected data

        //Send the request and get the response
        Response response = given(spec).get("{first}");
        response.prettyPrint();

        //Do assertion

        JsonPath jsonPath = response.jsonPath();

        response.
                then().
                statusCode(200).
                body("data", hasSize(24)).
                body("data.employee_name", hasItems("Tiger Nixon", "Garrett Winters"))
        ;


    }
}

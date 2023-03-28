package homework;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;

public class Get07 extends HerOkuAppBaseUrl {
    /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                  {
                    "firstname": "Josh",
                    "lastname": "Allen",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "midnight snack"
                }
                (TestNG Soft assertion yapınız)
     */

    @Test
    public void get07() {
        //Set the url
        spec.pathParams("first", "booking", "second", 22);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Josh", "Allen", 111, true, bookingDatesPojo, "midnight snack");

        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        SoftAssert softAssert = new SoftAssert();
        JsonPath jsonPath = response.jsonPath();

        softAssert.assertEquals(response.statusCode(), 200);
        softAssert.assertEquals(jsonPath.getString("firstname"), "Josh");
        softAssert.assertEquals(jsonPath.getString("lastname"), "Allen");
        softAssert.assertEquals(jsonPath.getInt("totalprice"), 111);
        softAssert.assertEquals(jsonPath.getBoolean("depositpaid"), true);
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"), "midnight snack");


    }
}

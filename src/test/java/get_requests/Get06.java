package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/24
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
 {
    "firstname": "John",
    "lastname": "Smith",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
     */
    @Test
    public void get06() {

        spec.pathParams("first", "booking", "second", 24);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        //1.Yol

        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Josh"),
                        "lastname", equalTo("Allen"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2018-01-01"),
                        "bookingdates.checkout", equalTo("2019-01-01"),
                        "additionalneeds", equalTo("midnight snack"));

        //2.Yol :  JsonPath ile dogrulama

        JsonPath jsonPath = response.jsonPath();

        assertEquals("John", jsonPath.getString("firstname"));
        assertEquals("Smith", jsonPath.getString("lastname"));
        assertEquals(111, jsonPath.getInt("totalprice"));
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals("2018-01-01", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast", jsonPath.getString("additionalneeds"));

        //3.Yol :  Soft Assertion

        //1.Adim : Obje olustur
        SoftAssert softAssert = new SoftAssert();

        //2.Adim Assertion
        softAssert.assertEquals(jsonPath.getString("firstname"), "Josh", "firstname uyusmadi");
        softAssert.assertEquals(jsonPath.getString("lastname"), "Allen", "lastname uyusmadi");
        softAssert.assertEquals(jsonPath.getInt("totalprice"), 111, "totalprice uyusmadi");
        softAssert.assertTrue(jsonPath.getBoolean("depositpaid"), "depositpaid uyusmadi");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2018-01-01", "checkin uyusmadi");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2019-01-01", "checkout uyusmadi");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"), "midnight snack", "additionalneeds uyusmadi");

        //3.Adim assertAll()        //bu adımi yazmaz isek hata alsak bile passed olur
        softAssert.assertAll();

    }
}

package homework.herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import util.ObjectMapperUtils;
import static homework.herokuapp_smoketest.PostBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PutBooking extends HerOkuAppBaseUrl {

    /*
   "https://restful-booker.herokuapp.com/apidoc/index.html" dökümanını kullanarak;
    Bir booking oluşturan, o booking'i güncelleyen ve sonra silen ve bu adımları doğrulayan
     positif ve negatif testler içeren bir otomasyon testi yazınız.

     Given
        https://restful-booker.herokuapp.com/booking/{bookingId}
         {
         "firstname" : "Atmaca",
         "lastname" : "Lokantası",
         "totalprice" : 111,
         "depositpaid" : true,
         "bookingdates" : {
             "checkin" : "2018-01-01",
             "checkout" : "2019-01-01"
         },
         "additionalneeds" : "İftar"
            }

     When
        Sent the put request

     Then
        Status code should be like 201
     And
        Body should be like
             {
         "bookingid": 1619,
         "booking": {
             "firstname": "Ali",
             "lastname": "Brown",
             "totalprice": 111,
             "depositpaid": true,
             "bookingdates": {
                 "checkin": "2018-01-01",
                 "checkout": "2019-01-01"
             },
             "additionalneeds": "İftar"
         }
}

    */
    @Test
    public void put01() {
        //Set the url
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Ali", "Can", 111, true, bookingDatesPojo, "Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).put("/{first}/{second}");

        response.prettyPrint();

        //Do Assertion
        BookingPojo actualData = ObjectMapperUtils.convertJsontoJava(response.asString(), BookingPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200, response.statusCode());

        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());

        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());

        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());


    }
}

package homework.herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PostBooking extends HerOkuAppBaseUrl {
    /*
   "https://restful-booker.herokuapp.com/apidoc/index.html" dökümanını kullanarak;
    Bir booking oluşturan, o booking'i güncelleyen ve sonra silen ve bu adımları doğrulayan
     positif ve negatif testler içeren bir otomasyon testi yazınız.

     Given
        "https://restful-booker.herokuapp.com/booking"
         {
         "firstname" : "Kardelen",
         "lastname" : "Kardeşler",
         "totalprice" : 111,
         "depositpaid" : true,
         "bookingdates" : {
             "checkin" : "2018-01-01",
             "checkout" : "2019-01-01"
         },
         "additionalneeds" : "İftar"
            }

     When
        Sent the post request

     Then
        Status code should be like 200
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
    static int bookingId;

    @Test
    public void post01() {
        //Set the url
        spec.pathParam("first", "booking");

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Kardelen", "Kardeşler", 111, true, bookingDatesPojo, "İftar");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        BookingResponsePojo actualData = ObjectMapperUtils.convertJsontoJava(response.asString(), BookingResponsePojo.class);
        System.out.println("actualData = " + actualData);

        //Do assertion
        assertEquals(200, response.statusCode());

        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

        bookingId = actualData.getBookingid();
        System.out.println("bookinId = " + bookingId);

    }





}

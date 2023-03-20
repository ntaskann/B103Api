package post_request;

import base_urls.HerOkuAppBaseUrl;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

public class Post04 extends HerOkuAppBaseUrl {

    /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 },
                 "additionalneeds": "Breakfast"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        },
                                        "additionalneeds": "Breakfast"
                                     }
                                  }
     */
    @Test
    public void post04() {

        //Set the url
        spec.pathParam("first", "booking");

        //Set the expected data
        BookingDatesPojo obj = new BookingDatesPojo("2021-09-21", "2021-12-21");

    }
}

package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get03 {
/*
    Given
            https://jsonplaceholder.typicode.com/todos/23
    When
            User send GET Request to the URL
    Then
            HTTP Status Code should be 200
    And
            Response format should be “application/json”
    And
		    “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
    And
		    “completed” is false
    And
		    “userId” is 2

 */

    @Test
    public void get03() {
        //Set the URL
        String url = "https://jsonplaceholder.typicode.com/todos/23";

        //Set the expected data

        //Send the request and get the response
        Response response = given().when().get(url);

        //Do Assertion
        //1.Yol
        response.
                then().
                statusCode(200).
                contentType("application/json").
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed", equalTo(false)).
                body("userId", equalTo(2));
        /*
            body() methodu icerisinde kontrol saglıyoruz
         */

        //2.Yol
        response.
                then().
                statusCode(200).
                contentType("application/json").
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),
                        "userId", equalTo(2));
        /*
            Tek body() methodu içerisinde çoklu assertion yaparak soft assertion oluşturabilirsiniz.
            Fail durumunda body() içerisinde Java çalışmayı durdurmaz.
            Çoklu body() methodu ile assertion yapıldığında fail durumunda Java bir sonraki body() methodu öncesi çaılışmayı durdurur.
            Hard assertion kod calismasini durduracagi icin body icerisindeki datalarin tamamini kontrol edemeyiz
            Bu yuzden tum assertionlari tek body icinde yaptik
         */


    }
}

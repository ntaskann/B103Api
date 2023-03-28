package homework;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class GetPetStore extends PetStoreBaseUrl {
    /*
      1) https://petstore.swagger.io/ dokumanını kullanarak
     statüsü "available" olan "pet" sayısını bulup 100'den fazla olduğunu assert eden bir otomasyon testi yazınız.

    Given
        https://petstore.swagger.io/v2/pet/findByStatus?status=available

    When
        Send the request

    Then
       Assert that number of pets whose status is "available" is more than 100

     */

    @Test
    public void petStoreAvailablePets() {

        //Set the url
        spec.pathParams("first","pet","second","findByStatus").queryParam("status","available");

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        int petSayisi = response.jsonPath().getList("id").size();
        System.out.println("petSayisi = " + petSayisi);

        assertTrue(petSayisi > 100);
    }
}

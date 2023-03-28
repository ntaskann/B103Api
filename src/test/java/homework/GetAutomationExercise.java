package homework;

import base_urls.AutomationExerciseBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetAutomationExercise extends AutomationExerciseBaseUrl {


/*2)
	Given
		https://automationexercise.com/api/productsList
	When
		User sends Get request
	Then
		Assert that number of "Women" usertype is 12
		(Kadın usertype sayısının 12 olduğunu doğrulayın)

*/

    @Test
    public void automationExerciseTest(){
        //Set the url
        spec.pathParam("first","productsList");

        //Set the expected data

        //Send the request and get the response
        Response response=given().spec(spec).get("/{first}");
        response.jsonPath().prettyPrint();

        //Do assertion
        int kadinSayisi=response.jsonPath().getList("products.findAll{it.category.usertype.usertype=='Women'}").size();

        assertEquals(12,kadinSayisi);
    }


}

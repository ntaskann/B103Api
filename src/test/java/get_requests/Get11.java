package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
    /*
         Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Kannan Ahluwalia", "The Hon. Tara Chaturvedi" and "Damayanti Dubashi" are among the users
        And
            The female users are less than or equals to male users
            (Kadın kullanıcı sayısı erkek kullanıcı sayısından küçük yada eşit olamlı)
     */

    @Test
    public void get11() {
        //Set the url
        spec.pathParam("first", "users");

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do assertion
        //1.Yol
        response.
                then().
                statusCode(200).
                body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data", hasSize(10),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Harita Sinha Jr.", "Miss Chandradev Verma"));

        JsonPath jsonPath = response.jsonPath();

        List<String> genders = jsonPath.getList("data.gender");
        System.out.println("genders = " + genders);

        int kadinSayisi = 0;

        for (String w : genders) {
            if (w.equals("female")) {
                kadinSayisi++;
            }
        }
        System.out.println("kadinSayisi = " + kadinSayisi);

        assertFalse((kadinSayisi <= genders.size() - kadinSayisi));

        //2.Yol(kadin erkek sayilarinin karşilastirilmasi)
        List<String> kadinKullaniciList = jsonPath.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println("kadinKullaniciList = " + kadinKullaniciList);
        List<String> erkekKullaniciList = jsonPath.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println("erkekKullaniciList = " + erkekKullaniciList);

        assertFalse(kadinKullaniciList.size() <= erkekKullaniciList.size());
    }
}



package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import util.AuthenticationGmiBank;

import static util.AuthenticationGmiBank.generateToken;

public class GmiBankBaseUrl {

    protected RequestSpecification spec;

    @Before // Her test methodundan once calisir
    public void setUp() {
        spec = new RequestSpecBuilder().
                addHeader("Authorization", "Bearer " + generateToken()).
                setContentType(ContentType.JSON).
                setBaseUri("https://www.gmibank.com").
                build();
    }

}

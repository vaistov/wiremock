import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.security.ClientTokenAuthenticator;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class WireMockTest {

    WireMock wm = WireMock.create()
            .scheme("https")
            .host("w7eve.wiremockapi.cloud")
            .port(443)
            .authenticator(new ClientTokenAuthenticator("2a3e8b42500398676d7993097a473c7a"))
            .build();
    @Test
    void one() {
        var wq = wm.register(WireMock.get("/users/21").willReturn(ResponseDefinitionBuilder.okForJson("OK BOSS")));

        var as = given()
                .when()
                .auth()
                .basic("asdf","asdf")
                .headers("content-type", "application/json")
                .get("https://w7eve.wiremockapi.cloud/users/21")
                .then().log().all();
        System.out.println(as);
        wm.removeStubMapping(wq);
    }
}

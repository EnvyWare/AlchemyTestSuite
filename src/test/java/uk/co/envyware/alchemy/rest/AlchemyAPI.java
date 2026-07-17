package uk.co.envyware.alchemy.rest;

import io.restassured.RestAssured;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class AlchemyAPI {

    private static final String BASE_URL = "https://vamcjbi1o9.execute-api.eu-north-1.amazonaws.com";

    @Step("Check API end point is responsive")
    public void checkEndPointResponsive() {
        SerenityRest.given()
                .options(BASE_URL);
    }


}

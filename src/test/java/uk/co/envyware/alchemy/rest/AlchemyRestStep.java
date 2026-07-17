package uk.co.envyware.alchemy.rest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import org.hamcrest.Matchers;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class AlchemyRestStep {

    @Steps
    private AlchemyAPI alchemyAPI;

    @When("I check the end point is active")
    public void checkEndPointActive() {
        this.alchemyAPI.checkEndPointResponsive();
    }

    @Then("the response should give code {code}")
    public void checkEndPointResponseCode(int code) {
        restAssuredThat(response -> response.statusCode(code));
    }



}

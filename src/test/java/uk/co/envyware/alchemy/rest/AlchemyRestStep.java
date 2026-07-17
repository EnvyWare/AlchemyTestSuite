package uk.co.envyware.alchemy.rest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
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

    @When("I get the platforms")
    public void getPlatforms() {
        this.alchemyAPI.getPlatforms();
    }

    @Then("there should be a list of minimum size {size}")
    public void checkListMinSize(int size) {
        restAssuredThat(response -> response
                .contentType(ContentType.JSON)
                .body("", Matchers.hasSize(Matchers.greaterThanOrEqualTo(size))));
    }

    @Then("there should be a list of size {size}")
    public void checkListExactSize(int size) {
        restAssuredThat(response -> response
                .contentType(ContentType.JSON)
                .body("", Matchers.hasSize(size)));
    }

    @Then("it should contain the {platform} platform")
    public void checkPlatformExists(String platform) {
        restAssuredThat(response -> response
                .contentType(ContentType.JSON)
                .body("PK", Matchers.hasItem("PLATFORM#" + platform)));
    }

    @When("I get the exams for {platform}")
    public void getExams(String platform) {
        this.alchemyAPI.getExams(platform);
    }

    @Then("it should contain the {exam} exam")
    public void checkExamExists(String exam) {
        restAssuredThat(response -> response
                .contentType(ContentType.JSON)
                .body("SK", Matchers.hasItem("EXAM#" + exam)));
    }

    @When("I send an invalid request to the exams end point")
    public void getExamsWithNoPlatform() {
        this.alchemyAPI.getExamsWithNoSpecifiedPlatform();
    }

    @When("I send an invalid request to the questions end point")
    public void getQuestionsWithNoExam() {
        this.alchemyAPI.getQuestionsWithNoSpecifiedExam();
    }

    @Then("I get the error \"{error}\"")
    public void checkErrorIs(String error) {
        restAssuredThat(response -> response
                .contentType(ContentType.JSON)
                .body("error", Matchers.equalTo(error))
        );
    }



}

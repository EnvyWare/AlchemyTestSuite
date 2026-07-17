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

    @When("I get questions for the {platform} {exam} exam")
    public void getQuestionsForExam(String platform, String exam) {
        this.alchemyAPI.getQuestionsForExam(platform, exam);
    }

    @Then("there should be {int} questions")
    public void checkQuestionCount(int questions) {
        restAssuredThat(response -> response
                .contentType(ContentType.JSON)
                .body("", Matchers.hasSize(questions))
        );
    }


    @Then("the questions should be for the {exam} exam")
    public void checkQuestionCount(String exam) {
        restAssuredThat(response -> response
                .contentType(ContentType.JSON)
                .body("PK", Matchers.everyItem(Matchers.equalTo("EXAM#" + exam)))
        );
    }

    @When("I get category questions for the {platform} {exam} exam {category} category")
    public void getCategoryQuestions(String platform, String exam, String category) {
        this.alchemyAPI.getQuestionsForExam(platform, exam, category);
    }

    @Then("the questions should be in the {category} category")
    public void checkQuestionCategory(String category) {
        restAssuredThat(response -> response
                .contentType(ContentType.JSON)
                .body("Category", Matchers.everyItem(Matchers.equalTo(category)))
        );
    }

    @When("I upload a platform")
    public void postToPlatforms() {
        this.alchemyAPI.uploadUnauthenticatedPlatform();
    }

    @When("I upload an exam")
    public void postToExams() {
        this.alchemyAPI.uploadUnauthenticatedExam();
    }

    @When("I upload a question set")
    public void postToQuestions() {
        this.alchemyAPI.uploadUnauthenticatedQuestions();
    }



}

package uk.co.envyware.alchemy.rest;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class AlchemyAPI {

    private static final String BASE_URL = "https://vamcjbi1o9.execute-api.eu-north-1.amazonaws.com";
    private static final String PLATFORMS = "/platforms";
    private static final String EXAMS = "/exams";
    private static final String QUESTIONS = "/questions";

    @Step("Check API end point is responsive")
    public void checkEndPointResponsive() {
        SerenityRest.given()
                .options(BASE_URL);
    }

    @Step("Get platforms from end point")
    public void getPlatforms() {
        SerenityRest.given()
                .get(BASE_URL + PLATFORMS);
    }

    @Step("Get exams for {0}")
    public void getExams(String platform) {
        SerenityRest.given()
                .baseUri(BASE_URL + EXAMS)
                .queryParam("platform", platform)
                .get();
    }

    @Step("Get exams with no specified platform")
    public void getExamsWithNoSpecifiedPlatform() {
        SerenityRest.given().get(BASE_URL + EXAMS);
    }

    @Step("Get exams with no specified exam")
    public void getQuestionsWithNoSpecifiedExam() {
        SerenityRest.given().get(BASE_URL + QUESTIONS);
    }

    @Step("Get questions for exam")
    public void getQuestionsForExam(String platform, String exam) {
        SerenityRest.given()
                .baseUri(BASE_URL + QUESTIONS)
                .queryParam("platform", platform)
                .queryParam("exam", exam)
                .get();
    }

    @Step("Get category questions for exam")
    public void getQuestionsForExam(String platform, String exam, String category) {
        SerenityRest.given()
                .baseUri(BASE_URL + QUESTIONS)
                .queryParam("platform", platform)
                .queryParam("exam", exam)
                .queryParam("category", category)
                .get();
    }

    @Step("Upload an unauthenticated platform")
    public void uploadUnauthenticatedPlatform() {
        SerenityRest.given().post(BASE_URL + PLATFORMS);
    }

    @Step("Upload an unauthenticated exam")
    public void uploadUnauthenticatedExam() {
        SerenityRest.given().post(BASE_URL + EXAMS);
    }

    @Step("Upload an unauthenticated questions")
    public void uploadUnauthenticatedQuestions() {
        SerenityRest.given().post(BASE_URL + QUESTIONS);
    }


}

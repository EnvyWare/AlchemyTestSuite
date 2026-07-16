package uk.co.envyware.alchemy.pageobjects.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import uk.co.envyware.alchemy.pageobjects.action.HomePageActions;
import uk.co.envyware.alchemy.pageobjects.action.LearningModeActions;
import uk.co.envyware.alchemy.pageobjects.action.MockQuizActions;
import uk.co.envyware.alchemy.screenplay.navigation.HomePage;
import uk.co.envyware.alchemy.screenplay.navigation.LearningMode;
import uk.co.envyware.alchemy.screenplay.navigation.QuestionPage;

public class LearningModeStep {

    @Steps
    private HomePageActions homePageActions;

    @Steps
    private LearningModeActions learningModeActions;

    @Given("{actor} is in learning mode for the {platform} {exam} exam")
    public void isInLearningModeForTheSalesforceAdministratorExam(String actor, String platform, String exam) {
        this.homePageActions.openHomePage();
        this.homePageActions.selectPlatform(platform);
        this.learningModeActions.startLearningModeFor(platform, exam);
    }

    @When("{actor} starts learning mode for the {platform} {exam} exam")
    public void startsLearningModeForThePlatformExam(String actor, String platform, String exam) {
        this.learningModeActions.startLearningModeFor(platform, exam);
    }

    @Then("{actor} should see the learning mode quiz screen")
    public void shouldSeeTheLearningModeScreen(String actor) {
        this.learningModeActions.shouldSeeLearningModeScreen();
    }

    @Then("{actor} should see a Submit Answer button")
    public void shouldSeeASubmitAnswerButton(String actor) {
        this.learningModeActions.shouldSeeSubmitButton();
    }
}

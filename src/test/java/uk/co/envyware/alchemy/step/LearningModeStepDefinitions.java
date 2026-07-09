package uk.co.envyware.alchemy.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import uk.co.envyware.alchemy.navigation.HomePage;
import uk.co.envyware.alchemy.navigation.LearningMode;
import uk.co.envyware.alchemy.navigation.QuestionPage;

public class LearningModeStepDefinitions {

    @Given("{actor} is in learning mode for the {platform} {exam} exam")
    public void isInLearningModeForTheSalesforceAdministratorExam(Actor actor, String platform, String exam) {
        actor.attemptsTo(
                HomePage.open(),
                HomePage.selectPlatform(platform),
                LearningMode.startFor(platform, exam)
        );
    }

    @When("{actor} starts learning mode for the {platform} {exam} exam")
    public void startsLearningModeForThePlatformExam(Actor actor, String platform, String exam) {
        actor.attemptsTo(LearningMode.startFor(platform, exam));
    }

    @Then("{actor} should see the learning mode quiz screen")
    public void shouldSeeTheLearningModeScreen(Actor actor) {
        actor.attemptsTo(
                Ensure.that(QuestionPage.currentExamTitle()).contains("LEARNING MODE")
        );
    }

    @Then("{actor} should see a Submit Answer button")
    public void shouldSeeASubmitAnswerButton(Actor actor) {
        actor.attemptsTo(WaitUntil.the(QuestionPage.SUBMIT_BUTTON, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds());
    }
}

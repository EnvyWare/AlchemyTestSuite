package uk.co.envyware.alchemy.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.hamcrest.Matchers;
import uk.co.envyware.alchemy.navigation.Alchemy;
import uk.co.envyware.alchemy.navigation.HomePage;
import uk.co.envyware.alchemy.navigation.LearningMode;
import uk.co.envyware.alchemy.navigation.QuestionPage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class WebsiteStepDefinitions {

    @Given("the {platform} {exam} exam is available")
    public void platformExamIsAvailable(String platform, String exam) {
        var actor = OnStage.theActorInTheSpotlight();
        var platformId = Alchemy.getPlatformId(platform);
        var examId = Alchemy.getExamId(exam);

        actor.attemptsTo(HomePage.withExamCardsVisible(platformId, examId));
    }

    @When("{actor} starts a mock quiz for the {platform} {exam} exam")
    public void startsAMockQuizForThePlatformExam(Actor actor, String platform, String exam) {
        var platformId = Alchemy.getPlatformId(platform);
        var examId = Alchemy.getExamId(exam);

        actor.attemptsTo(Click.on(HomePage.launchButton(platformId, examId, Alchemy.MOCK_EXAM_LAUNCH)));
    }

    @Then("{actor} should see the mock quiz screen")
    public void shouldSeeTheMockQuizScreen(Actor actor) {
        actor.attemptsTo(Ensure.that(TheWebPage.source()).contains("Mock Quiz"));
    }

    @Then("{actor} should see the first question")
    public void shouldSeeFirstQuestion(Actor actor) {
        actor.should(seeThat("visible question", QuestionPage.currentQuestionNumber(), Matchers.equalTo(1)));
    }

    @Then("{actor} should not see answer feedback before finishing the quiz")
    public void shouldNotSeeTheAnswer(Actor actor) {
        actor.attemptsTo(Click.on(QuestionPage.answerButton("A")));
        actor.attemptsTo(Click.on(QuestionPage.NEXT_BUTTON));
        actor.should(seeThat("visible question", QuestionPage.currentQuestionNumber(), Matchers.equalTo(2)));
    }

    @When("{actor} chooses the {category} category")
    public void choosesTheCategory(Actor actor, String category) {
        actor.attemptsTo(
                WaitUntil.the(HomePage.CATEGORY_SELECTOR, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds(),
                WaitUntil.the(HomePage.CATEGORY_SELECTOR, WebElementStateMatchers.isEnabled()).forNoMoreThan(2).seconds(),
                SelectFromOptions.byValue(category).from(HomePage.CATEGORY_SELECTOR)
        );
    }

    @When("{actor} starts category learning mode")
    public void startsCategoryLearningMode(Actor actor) {
        actor.attemptsTo(LearningMode.startWithSelectedCategory());
    }

    @Then("the question category should be {category}")
    public void theQuestionCategoryShouldBe(String category) {
        var actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(WaitUntil.the(QuestionPage.QUIZ_CATEGORY, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds());
        actor.should(seeThat("question category", QuestionPage.currentCategory(), Matchers.equalTo(category)));
    }

}

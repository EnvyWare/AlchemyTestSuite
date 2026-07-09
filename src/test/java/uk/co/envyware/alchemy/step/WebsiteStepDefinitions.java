package uk.co.envyware.alchemy.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.page.TheWebPage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import uk.co.envyware.alchemy.api.WaitForTimer;
import uk.co.envyware.alchemy.navigation.Alchemy;
import uk.co.envyware.alchemy.navigation.HomePage;
import uk.co.envyware.alchemy.navigation.NavigateTo;
import uk.co.envyware.alchemy.navigation.QuestionPage;

import java.util.function.Function;

public class WebsiteStepDefinitions {

    @Given("{actor} is doing exam practice")
    public void isDoingExamPractice(Actor actor) {
        actor.wasAbleTo(NavigateTo.navigateToHomePage());
    }

    @Then("{actor} should see the home page")
    public void shouldSeeTheAlchemyHomePage(Actor actor) {
        actor.attemptsTo(Ensure.that(TheWebPage.source()).contains("Alchemy"));
    }

    @When("{actor} clicks the {platform} platform")
    public void clicksThePlatformCard(Actor actor, String platform) {
        var platformId = Alchemy.getPlatformId(platform);

        actor.attemptsTo(HomePage.withPlatformCardsVisible(platformId));
        actor.attemptsTo(Click.on(HomePage.platformCard(platformId)));
    }

    @Then("{actor} should see exams appear")
    public void shouldSeeExamsAppear(Actor actor) {
        actor.attemptsTo(WaitUntil.the(HomePage.EXAM_GRID, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds());
        actor.should(GivenWhenThen.seeThat("visible exam cards",HomePage.visibleExamCount(), Matchers.greaterThan(0)));
    }

    @Then("{actor} should not see exams appear")
    public void shouldNotSeeExamsAppear(Actor actor) {
        actor.attemptsTo(WaitUntil.the(HomePage.EXAM_GRID, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds());
        actor.should(GivenWhenThen.seeThat("visible exam cards",HomePage.visibleExamCount(), Matchers.equalTo(0)));
    }

    @Given("{actor} has selected the {platform} platform")
    public void hasSelectedThePlatform(Actor actor, String platform) {
        this.isDoingExamPractice(actor);
        this.shouldSeeTheAlchemyHomePage(actor);
        this.clicksThePlatformCard(actor, platform);
        this.shouldSeeExamsAppear(actor);
    }

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

    @Then("{actor} should see a countdown timer")
    public void shouldSeeACountDownTimer(Actor actor) {
        this.shouldSeeChangingTimer(actor, Matchers::lessThan);
    }

    @Then("{actor} should see a timer counting up")
    public void shouldSeeATimerCountingUp(Actor actor) {
        this.shouldSeeChangingTimer(actor, Matchers::greaterThan);
    }

    private void shouldSeeChangingTimer(Actor actor, Function<Integer, Matcher<Integer>> matcherSupplier) {
        actor.attemptsTo(WaitUntil.the(QuestionPage.QUIZ_TIMER, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds());
        var currentTime = QuestionPage.currentTime(actor).answeredBy(actor);

        actor.attemptsTo(WaitForTimer.toChangeFrom(() -> QuestionPage.currentTime(actor).answeredBy(actor), currentTime));

        var newTime = QuestionPage.currentTime(actor);

        actor.should(GivenWhenThen.seeThat("current quiz time", newTime, matcherSupplier.apply(currentTime)));
    }

    @Then("{actor} should see the first question")
    public void shouldSeeFirstQuestion(Actor actor) {
        actor.should(GivenWhenThen.seeThat("visible question", QuestionPage.currentQuestionNumber(actor), Matchers.equalTo(1)));
    }

    @Then("{actor} should not see answer feedback before finishing the quiz")
    public void shouldNotSeeTheAnswer(Actor actor) {
        actor.attemptsTo(Click.on(QuestionPage.answerButton("A")));
        actor.attemptsTo(Click.on(QuestionPage.NEXT_BUTTON));
        actor.should(GivenWhenThen.seeThat("visible question", QuestionPage.currentQuestionNumber(actor), Matchers.equalTo(2)));
    }

    @When("{actor} starts learning mode for the {platform} {exam} exam")
    public void startsLearningModeForThePlatformExam(Actor actor, String platform, String exam) {
        var platformId = Alchemy.getPlatformId(platform);
        var examId = Alchemy.getExamId(exam);

        actor.attemptsTo(Click.on(HomePage.launchButton(platformId, examId, Alchemy.LEARNING_MODE_LAUNCH)));
    }

    @Then("{actor} should see the learning mode quiz screen")
    public void shouldSeeTheLearningModeScreen(Actor actor) {
        actor.attemptsTo(Ensure.that(TheWebPage.source()).contains("Learning Mode"));
    }

    @Then("{actor} should see a Submit Answer button")
    public void shouldSeeASubmitAnswerButton(Actor actor) {
        actor.attemptsTo(WaitUntil.the(QuestionPage.SUBMIT_BUTTON, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds());
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
        actor.attemptsTo(Click.on(HomePage.STUDY_CATEGORY_BUTTON));
    }

    @Then("the question category should be {category}")
    public void theQuestionCategoryShouldBe(String category) {
        var actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(WaitUntil.the(QuestionPage.QUIZ_CATEGORY, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds());
        actor.should(GivenWhenThen.seeThat("question category", QuestionPage.currentCategory(), Matchers.equalTo(category)));
    }

}

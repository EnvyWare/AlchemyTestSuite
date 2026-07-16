package uk.co.envyware.alchemy.pageobjects.step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import uk.co.envyware.alchemy.api.Alchemy;
import uk.co.envyware.alchemy.pageobjects.action.HomePageActions;
import uk.co.envyware.alchemy.pageobjects.action.LearningModeActions;
import uk.co.envyware.alchemy.pageobjects.action.MockQuizActions;
import uk.co.envyware.alchemy.screenplay.navigation.HomePage;
import uk.co.envyware.alchemy.screenplay.navigation.LearningMode;

public class HomePageStep {

    @Steps
    private HomePageActions homePageActions;

    @Steps
    private LearningModeActions learningModeActions;

    @Steps
    private MockQuizActions mockQuizActions;

    @Given("{actor} is doing exam practice")
    public void isDoingExamPractice(String actor) {
        this.homePageActions.openHomePage();
    }

    @Then("{actor} should see the home page")
    public void shouldSeeTheAlchemyHomePage(String actor) {
        this.homePageActions.shouldSeeHomePageScreen();
    }

    @Then("{actor} should see exams appear")
    public void shouldSeeExamsAppear(String actor) {
        this.homePageActions.shouldSeeAvailableExams();
    }

    @Then("{actor} should not see exams appear")
    public void shouldNotSeeExamsAppear(String actor) {
        this.homePageActions.shouldNotSeeAvailableExams();
    }

    @When("{actor} clicks the {platform} platform")
    public void clicksThePlatformCard(String actor, String platform) {
        this.homePageActions.selectPlatform(platform);
    }

    @Given("{actor} has selected the {platform} platform")
    public void hasSelectedThePlatform(String actor, String platform) {
        this.homePageActions.openHomePage();
        this.homePageActions.selectPlatform(platform);
    }

    @Given("the {platform} {exam} exam is available")
    public void platformExamIsAvailable(String platform, String exam) {
        this.homePageActions.shouldSeeAvailableExams();
        this.homePageActions.shouldSeePlatformExamCard(platform, exam);
    }

    @When("{actor} starts a mock quiz for the {platform} {exam} exam")
    public void startsAMockQuizForThePlatformExam(String actor, String platform, String exam) {
        this.mockQuizActions.startMockQuizFor(platform, exam);
    }

    @When("{actor} chooses the {category} category")
    public void choosesTheCategory(String actor, String category) {
        this.mockQuizActions.selectCategory(category);
    }

    @When("{actor} starts category learning mode")
    public void startsCategoryLearningMode(String actor) {
        this.mockQuizActions.startLearningModeForCategory();
    }
}

package uk.co.envyware.alchemy.pageobjects.step;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.hamcrest.Matchers;
import uk.co.envyware.alchemy.pageobjects.action.HomePageActions;
import uk.co.envyware.alchemy.pageobjects.action.LearningModeActions;
import uk.co.envyware.alchemy.pageobjects.action.QuestionPageActions;
import uk.co.envyware.alchemy.screenplay.navigation.QuestionPage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuizPageStep {

    @Steps
    private HomePageActions homePageActions;

    @Steps
    private LearningModeActions learningModeActions;

    @Steps
    private QuestionPageActions questionPageActions;

    @Then("{actor} should see a countdown timer")
    public void shouldSeeACountDownTimer(String actor) {
        assertThat(this.questionPageActions.isTimerCountingUp()).isFalse();
    }

    @Then("{actor} should see a timer counting up")
    public void shouldSeeATimerCountingUp(String actor) {
        assertThat(this.questionPageActions.isTimerCountingUp()).isTrue();
    }

    @When("{actor} selects the answer {answer}")
    public void submitsTheAnswer(String actor, String answer) {
        this.questionPageActions.selectAnswer(answer);
    }

    @When("{actor} submits the answer")
    public void submitsTheAnswer(String actor) {
        this.questionPageActions.submitAnswer();
    }

    @Then("{actor} should see whether the answer was correct or incorrect")
    public void seesWhetherTheAnswerWasCorrectOrIncorrect(String actor) {
        assertThat(this.questionPageActions.shouldSeeLearningModeFeedback()).isTrue();
    }

    @Then("{actor} should not see whether the answer was correct or incorrect")
    public void doesNotSeeWhetherTheAnswerWasCorrectOrIncorrect(String actor) {
        assertThat(this.questionPageActions.shouldSeeLearningModeFeedback()).isFalse();
    }

    @Then("{actor} should see the explanation for the question")
    public void seesTheExplanationForTheQuestion(String actor) {
        assertThat(this.questionPageActions.shouldSeeLearningModeExplanation()).isTrue();
    }

    @Then("{actor} should be able to move to the next question")
    public void canMoveToNextQuestion(String actor) {
        assertThat(this.questionPageActions.shouldMoveToNextQuestion()).isTrue();
    }

    @Then("{actor} should be able to go back to the previous question")
    public void canMoveToPreviousQuestion(String actor) {
        assertThat(this.questionPageActions.shouldMoveToPreviousQuestion()).isTrue();
    }

    @Then("{actor} should see the mock quiz screen")
    public void shouldSeeTheMockQuizScreen(String actor) {
        this.learningModeActions.shouldNotSeeLearningModeScreen();
    }

    @Then("{actor} should see the first question")
    public void shouldSeeFirstQuestion(String actor) {
        assertThat(this.questionPageActions.getCurrentQuestionNumber()).isEqualTo(1);
    }

    @Then("the question category should be {category}")
    public void theQuestionCategoryShouldBe(String category) {
        assertThat(this.questionPageActions.getQuestionCategory()).isEqualTo(category);
    }

    @Then("{actor} should not see answer feedback before finishing the quiz")
    public void shouldNotSeeTheAnswer(String actor) {
        this.questionPageActions.selectAnswer("A");
        this.questionPageActions.moveToNextQuestion();
        assertThat(this.questionPageActions.getCurrentQuestionNumber()).isEqualTo(2);
    }
}

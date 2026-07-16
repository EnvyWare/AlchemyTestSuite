package uk.co.envyware.alchemy.screenplay.step;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.hamcrest.Matchers;
import uk.co.envyware.alchemy.api.WaitForValue;
import uk.co.envyware.alchemy.screenplay.navigation.QuestionPage;
import uk.co.envyware.alchemy.screenplay.question.TheQuizTimer;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class QuizPageStepDefinitions {

    @Then("{actor} should see a countdown timer")
    public void shouldSeeACountDownTimer(Actor actor) {
        actor.should(seeThat(
                "Timer counts up",
                TheQuizTimer.isCountingUp(),
                equalTo(false)
        ));
    }

    @Then("{actor} should see a timer counting up")
    public void shouldSeeATimerCountingUp(Actor actor) {
        actor.should(
                seeThat(
                "Timer counts up",
                TheQuizTimer.isCountingUp(),
                equalTo(true)
        ));
    }

    @When("{actor} selects the answer {answer}")
    public void submitsTheAnswer(Actor actor, String answer) {
        actor.attemptsTo(QuestionPage.selectAnswer(answer));
    }

    @When("{actor} submits the answer")
    public void submitsTheAnswer(Actor actor) {
        actor.attemptsTo(QuestionPage.submitAnswer());
    }

    @Then("{actor} should see whether the answer was correct or incorrect")
    public void seesWhetherTheAnswerWasCorrectOrIncorrect(Actor actor) {
        actor.attemptsTo(WaitUntil.the(QuestionPage.LEARNING_FEEDBACK, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds());
    }

    @Then("{actor} should not see whether the answer was correct or incorrect")
    public void doesNotSeeWhetherTheAnswerWasCorrectOrIncorrect(Actor actor) {
        actor.attemptsTo(WaitUntil.the(QuestionPage.LEARNING_FEEDBACK, WebElementStateMatchers.isNotVisible()).forNoMoreThan(2).seconds());
    }

    @Then("{actor} should see the explanation for the question")
    public void seesTheExplanationForTheQuestion(Actor actor) {
        actor.attemptsTo(WaitUntil.the(QuestionPage.LEARNING_FEEDBACK_EXPLANATION, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds());
    }

    @Then("{actor} should be able to move to the next question")
    public void canMoveToNextQuestion(Actor actor) {
        actor.attemptsTo(WaitUntil.the(QuestionPage.NEXT_BUTTON, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds());

        var currentQuestionNumber = QuestionPage.currentQuestionNumber().answeredBy(actor);

        actor.attemptsTo(
                Click.on(QuestionPage.NEXT_BUTTON),
                WaitForValue.toChangeFrom(actor, currentQuestionNumber, () -> QuestionPage.currentQuestionNumber().answeredBy(actor))
        );

        var newQuestionNumber = QuestionPage.currentQuestionNumber();

        actor.should(seeThat("current quiz time", newQuestionNumber, Matchers.greaterThan(currentQuestionNumber)));
    }

    @Then("{actor} should be able to go back to the previous question")
    public void canMoveToPreviousQuestion(Actor actor) {
        actor.attemptsTo(
                WaitUntil.the(QuestionPage.BACK_BUTTON, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds(),
                WaitUntil.the(QuestionPage.BACK_BUTTON, WebElementStateMatchers.isClickable()).forNoMoreThan(2).seconds()
        );

        var currentQuestionNumber = QuestionPage.currentQuestionNumber().answeredBy(actor);

        actor.attemptsTo(
                Click.on(QuestionPage.BACK_BUTTON),
                WaitForValue.toChangeFrom(actor, currentQuestionNumber, () -> QuestionPage.currentQuestionNumber().answeredBy(actor))
        );

        var newQuestionNumber = QuestionPage.currentQuestionNumber();

        actor.should(seeThat("current quiz time", newQuestionNumber, Matchers.lessThan(currentQuestionNumber)));
    }
}

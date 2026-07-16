package uk.co.envyware.alchemy.screenplay.question;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import uk.co.envyware.alchemy.api.WaitForValue;
import uk.co.envyware.alchemy.screenplay.navigation.QuestionPage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class TheQuizTimer {

    public static Question<Boolean> isCountingUp() {
        return Question.about("whether the quiz timer is counting down")
                .answeredBy(actor -> {
                    actor.attemptsTo(WaitUntil.the(QuestionPage.QUIZ_TIMER, WebElementStateMatchers.isVisible()).forNoMoreThan(10).seconds());
                    actor.should(seeThat(isQuizTimerVisible()));

                    var firstValue = QuestionPage.currentTime().answeredBy(actor);

                    actor.attemptsTo(
                            WaitForValue.toChangeFrom(
                                    actor,
                                    firstValue,
                                    () -> QuestionPage.currentTime().answeredBy(actor)
                            )
                    );

                    var secondValue = QuestionPage.currentTime().answeredBy(actor);

                    return firstValue < secondValue;
                });
    }

    public static Question<Boolean> isQuizTimerVisible() {
        return Question.about("whether the quiz timer is visible")
                .answeredBy(actor -> QuestionPage.QUIZ_TIMER.resolveFor(actor).isVisible());
    }

}

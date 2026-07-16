package uk.co.envyware.alchemy.screenplay.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import uk.co.envyware.alchemy.api.Alchemy;

public class LearningMode {

    public static Performable startFor(String platform, String exam) {
        return Click.on(HomePage.launchButton(platform, exam, Alchemy.LEARNING_MODE_LAUNCH))
                .then(ensureLearningModeLoaded());
    }

    public static Performable startWithSelectedCategory() {
        return Click.on(HomePage.STUDY_CATEGORY_BUTTON)
                .then(ensureLearningModeLoaded());
    }

    public static Performable ensureLearningModeLoaded() {
        return WaitUntil.the(QuestionPage.QUIZ_EXAM_TITLE, WebElementStateMatchers.isVisible())
                .forNoMoreThan(2)
                .seconds();
    }
}

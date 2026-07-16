package uk.co.envyware.alchemy.screenplay.navigation;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class HomePage {

    public static final Target PLATFORM_GRID = Target.the("platform grid")
            .locatedBy("[data-testid='platforms-grid']");

    public static final Target PLATFORM_CARDS = Target.the("platform cards")
            .locatedBy("[data-testid='platform-card']");

    private static final Target PLATFORM_CARD_BY_ID = Target.the("{0} platform card")
            .locatedBy("[data-testid='platform-card'][data-platform-id='{0}']");

    public static final Target EXAM_GRID = Target.the("exam grid")
            .locatedBy("[data-testid='exams-grid']");

    private static final Target EXAM_CARDS = Target.the("exam cards")
            .locatedBy("[data-testid='exam-card']");

    private static final Target EXAM_CARD_BY_ID = Target.the("{0} {1} exam card")
            .locatedBy("[data-testid='exam-card'][data-platform-id='{0}'][data-exam-id='{1}']");

    private static final Target LAUNCH_BUTTON_BY_ID = Target.the("{0} {1} {2} launch button")
            .locatedBy("[data-testid='exam-card'][data-platform-id='{0}'][data-exam-id='{1}'] [data-testid='{2}']");

    public static final Target CATEGORY_SELECTOR = Target.the("category selector")
            .locatedBy("[data-testid='focused-learning-category-select']");

    public static final Target STUDY_CATEGORY_BUTTON = Target.the("study category")
            .locatedBy("[data-testid='study-category-button']");

    public static Target platformCard(String platformId) {
        return PLATFORM_CARD_BY_ID.of(platformId);
    }

    public static Target examCard(String platformId, String examId) {
        return EXAM_CARD_BY_ID.of(platformId, examId);
    }

    public static Performable open() {
        return Task.where("{0} opens the Alchemy home page",
                Open.browserOn().the(AlchemyHomePage.class));
    }

    public static Performable selectPlatform(String platform) {
        return Click.on(HomePage.platformCard(platform));
    }

    public static Performable withPlatformCardsVisible(String platformId) {
        return Task.where("{0} sees the available platform cards",
                WaitUntil.the(HomePage.PLATFORM_GRID, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds(),
                WaitUntil.the(HomePage.platformCard(platformId), WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds()
        );
    }

    public static Question<Integer> visibleExamCount() {
        return actor -> (int) HomePage.EXAM_CARDS
                .resolveAllFor(actor)
                .stream()
                .filter(WebElementFacade::isVisible)
                .count();
    }

    public static Performable withExamCardsVisible(String platformId, String examId) {
        return Task.where("{0} sees the available exam cards",
                WaitUntil.the(HomePage.EXAM_GRID, WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds(),
                WaitUntil.the(HomePage.examCard(platformId, examId), WebElementStateMatchers.isVisible()).forNoMoreThan(2).seconds()
        );
    }

    public static Target launchButton(String platformId, String examId, String buttonId) {
        return LAUNCH_BUTTON_BY_ID.of(platformId, examId, buttonId);
    }
}
